package br.com.vitalis.cidalia.service;

import br.com.vitalis.cidalia.domain.Atendimento;
import br.com.vitalis.cidalia.domain.StatusAtendimento;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.dto.AtendimentoRequest;
import br.com.vitalis.cidalia.dto.AtendimentoResponse;
import br.com.vitalis.cidalia.dto.AtualizarAtendimentoRequest;
import br.com.vitalis.cidalia.exception.BusinessException;
import br.com.vitalis.cidalia.exception.ResourceNotFoundException;
import br.com.vitalis.cidalia.mapper.AtendimentoMapper;
import br.com.vitalis.cidalia.repository.AtendimentoRepository;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AtendimentoService {

    private final AtendimentoRepository repository;
    private final OcorrenciaService ocorrenciaService;
    private final EquipeService equipeService;
    private final VeiculoService veiculoService;
    private final CodigoOperacionalFactory codigoFactory;
    private final AtendimentoMapper mapper;

    public AtendimentoService(
            AtendimentoRepository repository,
            OcorrenciaService ocorrenciaService,
            EquipeService equipeService,
            VeiculoService veiculoService,
            CodigoOperacionalFactory codigoFactory,
            AtendimentoMapper mapper
    ) {
        this.repository = repository;
        this.ocorrenciaService = ocorrenciaService;
        this.equipeService = equipeService;
        this.veiculoService = veiculoService;
        this.codigoFactory = codigoFactory;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public List<AtendimentoResponse> listar() {
        return repository.findAll().stream()
                .map(mapper::toResponse)
                .toList();
    }

    @Transactional
    public AtendimentoResponse criar(AtendimentoRequest request) {
        if (repository.existsByOcorrenciaId(request.ocorrenciaId())) {
            throw new BusinessException("Esta ocorrencia ja possui atendimento vinculado.");
        }

        var ocorrencia = ocorrenciaService.buscarEntidade(request.ocorrenciaId());
        var equipe = equipeService.buscarEntidade(request.equipeId());
        var veiculo = veiculoService.buscarEntidade(request.veiculoId());

        if (equipe.getStatus() != StatusOperacional.DISPONIVEL) {
            throw new BusinessException("Equipe selecionada nao esta disponivel.");
        }
        if (veiculo.getStatus() != StatusOperacional.DISPONIVEL) {
            throw new BusinessException("Veiculo selecionado nao esta disponivel.");
        }

        var atendimento = new Atendimento();
        atendimento.setCodigo(gerarCodigoUnico());
        atendimento.setOcorrencia(ocorrencia);
        atendimento.setEquipe(equipe);
        atendimento.setVeiculo(veiculo);
        atendimento.setDistanciaKm(request.distanciaKm());
        atendimento.setTempoEstimadoMinutos(request.tempoEstimadoMinutos());
        atendimento.setRotaResumo(request.rotaResumo());
        atendimento.setObservacoes(request.observacoes());
        atendimento.setStatus(StatusAtendimento.DESPACHADO);
        atendimento.setDespachoEm(LocalDateTime.now());

        ocorrencia.setStatus(StatusOcorrencia.DESPACHADA);
        equipe.setStatus(StatusOperacional.EM_ATENDIMENTO);
        veiculo.setStatus(StatusOperacional.EM_ATENDIMENTO);

        return mapper.toResponse(repository.save(atendimento));
    }

    @Transactional
    public AtendimentoResponse atualizar(Long id, AtualizarAtendimentoRequest request) {
        var atendimento = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Atendimento", id));

        atendimento.setStatus(request.status());
        if (request.observacoes() != null && !request.observacoes().isBlank()) {
            atendimento.setObservacoes(request.observacoes());
        }

        if (request.status() == StatusAtendimento.NO_LOCAL && atendimento.getChegadaEm() == null) {
            atendimento.setChegadaEm(LocalDateTime.now());
        }

        if (request.status() == StatusAtendimento.FINALIZADO || request.status() == StatusAtendimento.CANCELADO) {
            atendimento.setEncerradoEm(LocalDateTime.now());
            atendimento.getEquipe().setStatus(StatusOperacional.DISPONIVEL);
            atendimento.getVeiculo().setStatus(StatusOperacional.DISPONIVEL);
            atendimento.getOcorrencia().setStatus(
                    request.status() == StatusAtendimento.FINALIZADO ? StatusOcorrencia.CONCLUIDA : StatusOcorrencia.CANCELADA
            );
        } else if (request.status() == StatusAtendimento.EM_ROTA) {
            atendimento.getOcorrencia().setStatus(StatusOcorrencia.EM_ATENDIMENTO);
        }

        return mapper.toResponse(atendimento);
    }

    private String gerarCodigoUnico() {
        long sequencial = repository.count() + 1;
        String codigo = codigoFactory.criarCodigoAtendimento(sequencial);
        while (repository.existsByCodigo(codigo)) {
            sequencial++;
            codigo = codigoFactory.criarCodigoAtendimento(sequencial);
        }
        return codigo;
    }
}
