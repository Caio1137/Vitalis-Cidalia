package br.com.vitalis.cidalia.service;

import br.com.vitalis.cidalia.domain.Ocorrencia;
import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import br.com.vitalis.cidalia.dto.OcorrenciaRequest;
import br.com.vitalis.cidalia.dto.OcorrenciaResponse;
import br.com.vitalis.cidalia.exception.ResourceNotFoundException;
import br.com.vitalis.cidalia.mapper.OcorrenciaMapper;
import br.com.vitalis.cidalia.repository.OcorrenciaRepository;
import java.util.List;
import java.util.Locale;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OcorrenciaService {

    private final OcorrenciaRepository repository;
    private final PontoAtendimentoService pontoService;
    private final CodigoOperacionalFactory codigoFactory;
    private final OcorrenciaMapper mapper;

    public OcorrenciaService(
            OcorrenciaRepository repository,
            PontoAtendimentoService pontoService,
            CodigoOperacionalFactory codigoFactory,
            OcorrenciaMapper mapper
    ) {
        this.repository = repository;
        this.pontoService = pontoService;
        this.codigoFactory = codigoFactory;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<OcorrenciaResponse> listar(StatusOcorrencia status, PrioridadeOcorrencia prioridade, TipoOcorrencia tipo, String setor) {
        var setorNormalizado = setor == null || setor.isBlank() ? null : setor.toLowerCase(Locale.ROOT);
        return repository.filtrar(status, prioridade, tipo, setorNormalizado).stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public OcorrenciaResponse criar(OcorrenciaRequest request) {
        var ocorrencia = mapper.toEntity(request);
        ocorrencia.setPontoDestino(pontoService.buscarEntidade(request.pontoDestinoId()));
        ocorrencia.setProtocolo(gerarProtocoloUnico());
        return mapper.toResponse(repository.save(ocorrencia));
    }

    @Transactional(readOnly = true)
    public Ocorrencia buscarEntidade(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Ocorrencia", id));
    }

    @Transactional
    public OcorrenciaResponse atualizarStatus(Long id, StatusOcorrencia status) {
        var ocorrencia = buscarEntidade(id);
        ocorrencia.setStatus(status);
        return mapper.toResponse(ocorrencia);
    }

    @Transactional(readOnly = true)
    public List<Ocorrencia> listarEntidades() {
        return repository.findAll();
    }

    private String gerarProtocoloUnico() {
        long sequencial = repository.count() + 1;
        String protocolo = codigoFactory.criarProtocoloOcorrencia(sequencial);
        while (repository.existsByProtocolo(protocolo)) {
            sequencial++;
            protocolo = codigoFactory.criarProtocoloOcorrencia(sequencial);
        }
        return protocolo;
    }
}
