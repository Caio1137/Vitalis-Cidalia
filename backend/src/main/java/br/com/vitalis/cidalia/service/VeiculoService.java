package br.com.vitalis.cidalia.service;

import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.domain.Veiculo;
import br.com.vitalis.cidalia.dto.VeiculoRequest;
import br.com.vitalis.cidalia.dto.VeiculoResponse;
import br.com.vitalis.cidalia.exception.BusinessException;
import br.com.vitalis.cidalia.exception.ResourceNotFoundException;
import br.com.vitalis.cidalia.mapper.VeiculoMapper;
import br.com.vitalis.cidalia.repository.VeiculoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VeiculoService extends AbstractCrudService<Veiculo> {

    private final VeiculoRepository repository;
    private final VeiculoMapper mapper;

    public VeiculoService(VeiculoRepository repository, VeiculoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<VeiculoResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public VeiculoResponse criar(VeiculoRequest request) {
        return mapper.toResponse(salvar(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    public Veiculo buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Veiculo", id));
    }

    @Transactional
    public VeiculoResponse atualizarStatus(Long id, StatusOperacional status) {
        var veiculo = buscarEntidade(id);
        veiculo.setStatus(status);
        return mapper.toResponse(veiculo);
    }

    @Override
    protected void validarAntesDeSalvar(Veiculo veiculo) {
        if (repository.existsByPlacaIgnoreCase(veiculo.getPlaca())) {
            throw new BusinessException("Ja existe veiculo cadastrado com esta placa.");
        }
    }

    @Override
    protected Veiculo persistir(Veiculo veiculo) {
        return repository.save(veiculo);
    }
}
