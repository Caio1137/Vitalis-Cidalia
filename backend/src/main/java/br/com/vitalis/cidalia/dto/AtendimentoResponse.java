package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusAtendimento;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public record AtendimentoResponse(
        Long id,
        String codigo,
        OcorrenciaResponse ocorrencia,
        EquipeResponse equipe,
        VeiculoResponse veiculo,
        StatusAtendimento status,
        LocalDateTime despachoEm,
        LocalDateTime chegadaEm,
        LocalDateTime encerradoEm,
        BigDecimal distanciaKm,
        Integer tempoEstimadoMinutos,
        String rotaResumo,
        String observacoes
) {
}
