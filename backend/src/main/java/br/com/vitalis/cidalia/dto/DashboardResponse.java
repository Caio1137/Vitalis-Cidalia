package br.com.vitalis.cidalia.dto;

import java.util.List;

public record DashboardResponse(
        long ocorrenciasAbertas,
        long ocorrenciasCriticas,
        long equipesDisponiveis,
        long veiculosDisponiveis,
        long atendimentosEmAndamento,
        List<OcorrenciaResponse> ultimasOcorrencias,
        List<AtendimentoResponse> ultimosAtendimentos
) {
}
