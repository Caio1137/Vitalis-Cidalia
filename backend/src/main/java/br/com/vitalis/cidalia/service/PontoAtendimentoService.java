package br.com.vitalis.cidalia.service;

import br.com.vitalis.cidalia.domain.PontoAtendimento;
import br.com.vitalis.cidalia.dto.PontoAtendimentoRequest;
import br.com.vitalis.cidalia.dto.PontoAtendimentoResponse;
import br.com.vitalis.cidalia.exception.BusinessException;
import br.com.vitalis.cidalia.exception.ResourceNotFoundException;
import br.com.vitalis.cidalia.mapper.PontoAtendimentoMapper;
import br.com.vitalis.cidalia.repository.PontoAtendimentoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PontoAtendimentoService extends AbstractCrudService<PontoAtendimento> {

    private final PontoAtendimentoRepository repository;
    private final PontoAtendimentoMapper mapper;

    public PontoAtendimentoService(PontoAtendimentoRepository repository, PontoAtendimentoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<PontoAtendimentoResponse> listar() {
        return repository.findByAtivoTrueOrderByNomeAsc().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public PontoAtendimentoResponse criar(PontoAtendimentoRequest request) {
        return mapper.toResponse(salvar(mapper.toEntity(request)));
    }

    @Transactional(readOnly = true)
    public PontoAtendimento buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ponto de atendimento", id));
    }

    @Transactional
    public void inativar(Long id) {
        var ponto = buscarEntidade(id);
        if (!ponto.getOcorrencias().isEmpty()) {
            throw new BusinessException("Nao e permitido inativar ponto com ocorrencias vinculadas.");
        }
        ponto.setAtivo(false);
    }

    @Override
    protected void validarAntesDeSalvar(PontoAtendimento ponto) {
        if (repository.existsByNomeIgnoreCase(ponto.getNome())) {
            throw new BusinessException("Ja existe ponto de atendimento com este nome.");
        }
    }

    @Override
    protected PontoAtendimento persistir(PontoAtendimento ponto) {
        return repository.save(ponto);
    }
}
