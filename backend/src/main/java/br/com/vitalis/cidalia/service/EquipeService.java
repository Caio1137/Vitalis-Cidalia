package br.com.vitalis.cidalia.service;

import br.com.vitalis.cidalia.domain.Equipe;
import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.dto.EquipeRequest;
import br.com.vitalis.cidalia.dto.EquipeResponse;
import br.com.vitalis.cidalia.exception.BusinessException;
import br.com.vitalis.cidalia.exception.ResourceNotFoundException;
import br.com.vitalis.cidalia.mapper.EquipeMapper;
import br.com.vitalis.cidalia.repository.EquipeRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EquipeService extends AbstractCrudService<Equipe> {

    private final EquipeRepository repository;
    private final VeiculoService veiculoService;
    private final EquipeMapper mapper;

    public EquipeService(EquipeRepository repository, VeiculoService veiculoService, EquipeMapper mapper) {
        this.repository = repository;
        this.veiculoService = veiculoService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<EquipeResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public EquipeResponse criar(EquipeRequest request) {
        var equipe = mapper.toEntity(request);
        if (request.veiculoId() != null) {
            equipe.setVeiculo(veiculoService.buscarEntidade(request.veiculoId()));
        }
        return mapper.toResponse(salvar(equipe));
    }

    @Transactional(readOnly = true)
    public Equipe buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Equipe", id));
    }

    @Transactional
    public EquipeResponse atualizarStatus(Long id, StatusOperacional status) {
        var equipe = buscarEntidade(id);
        equipe.setStatus(status);
        return mapper.toResponse(equipe);
    }

    @Override
    protected void validarAntesDeSalvar(Equipe equipe) {
        if (repository.existsByNomeIgnoreCase(equipe.getNome())) {
            throw new BusinessException("Ja existe equipe cadastrada com este nome.");
        }
    }

    @Override
    protected Equipe persistir(Equipe equipe) {
        return repository.save(equipe);
    }
}
