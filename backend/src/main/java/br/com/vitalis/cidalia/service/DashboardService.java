package br.com.vitalis.cidalia.service;

import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.StatusAtendimento;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.dto.DashboardResponse;
import br.com.vitalis.cidalia.mapper.AtendimentoMapper;
import br.com.vitalis.cidalia.mapper.OcorrenciaMapper;
import br.com.vitalis.cidalia.repository.AtendimentoRepository;
import br.com.vitalis.cidalia.repository.EquipeRepository;
import br.com.vitalis.cidalia.repository.OcorrenciaRepository;
import br.com.vitalis.cidalia.repository.VeiculoRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class DashboardService {

    // Padrao Singleton: por padrao o Spring mantem uma unica instancia deste service no contexto.
    private final OcorrenciaRepository ocorrenciaRepository;
    private final EquipeRepository equipeRepository;
    private final VeiculoRepository veiculoRepository;
    private final AtendimentoRepository atendimentoRepository;
    private final OcorrenciaMapper ocorrenciaMapper;
    private final AtendimentoMapper atendimentoMapper;

    public DashboardService(
            OcorrenciaRepository ocorrenciaRepository,
            EquipeRepository equipeRepository,
            VeiculoRepository veiculoRepository,
            AtendimentoRepository atendimentoRepository,
            OcorrenciaMapper ocorrenciaMapper,
            AtendimentoMapper atendimentoMapper
    ) {
        this.ocorrenciaRepository = ocorrenciaRepository;
        this.equipeRepository = equipeRepository;
        this.veiculoRepository = veiculoRepository;
        this.atendimentoRepository = atendimentoRepository;
        this.ocorrenciaMapper = ocorrenciaMapper;
        this.atendimentoMapper = atendimentoMapper;
    }

    @Transactional(readOnly = true)
    public DashboardResponse carregarResumo() {
        var statusFechados = List.of(StatusOcorrencia.CONCLUIDA, StatusOcorrencia.CANCELADA);
        var statusAtivos = List.of(StatusAtendimento.DESPACHADO, StatusAtendimento.EM_ROTA, StatusAtendimento.NO_LOCAL);

        // Padrao Iterator: os streams percorrem colecoes para montar as listas do painel.
        return new DashboardResponse(
                ocorrenciaRepository.countByStatusNotIn(statusFechados),
                ocorrenciaRepository.countByPrioridadeAndStatusNotIn(PrioridadeOcorrencia.CRITICA, statusFechados),
                equipeRepository.countByStatus(StatusOperacional.DISPONIVEL),
                veiculoRepository.countByStatus(StatusOperacional.DISPONIVEL),
                atendimentoRepository.countByStatusIn(statusAtivos),
                ocorrenciaRepository.findTop5ByOrderByRegistradaEmDesc().stream().map(ocorrenciaMapper::toResponse).toList(),
                atendimentoRepository.findTop5ByOrderByDespachoEmDesc().stream().map(atendimentoMapper::toResponse).toList()
        );
    }
}
