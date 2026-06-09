package br.com.vitalis.cidalia.mapper;

import br.com.vitalis.cidalia.domain.Atendimento;
import br.com.vitalis.cidalia.dto.AtendimentoResponse;
import org.springframework.stereotype.Component;

@Component
public class AtendimentoMapper {

    private final OcorrenciaMapper ocorrenciaMapper;
    private final EquipeMapper equipeMapper;
    private final VeiculoMapper veiculoMapper;

    public AtendimentoMapper(OcorrenciaMapper ocorrenciaMapper, EquipeMapper equipeMapper, VeiculoMapper veiculoMapper) {
        this.ocorrenciaMapper = ocorrenciaMapper;
        this.equipeMapper = equipeMapper;
        this.veiculoMapper = veiculoMapper;
    }

    public AtendimentoResponse toResponse(Atendimento atendimento) {
        return new AtendimentoResponse(
                atendimento.getId(),
                atendimento.getCodigo(),
                ocorrenciaMapper.toResponse(atendimento.getOcorrencia()),
                equipeMapper.toResponse(atendimento.getEquipe()),
                veiculoMapper.toResponse(atendimento.getVeiculo()),
                atendimento.getStatus(),
                atendimento.getDespachoEm(),
                atendimento.getChegadaEm(),
                atendimento.getEncerradoEm(),
                atendimento.getDistanciaKm(),
                atendimento.getTempoEstimadoMinutos(),
                atendimento.getRotaResumo(),
                atendimento.getObservacoes()
        );
    }
}
