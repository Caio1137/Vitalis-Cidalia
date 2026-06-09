package br.com.vitalis.cidalia.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record AtendimentoRequest(
        @NotNull Long ocorrenciaId,
        @NotNull Long equipeId,
        @NotNull Long veiculoId,
        @Positive BigDecimal distanciaKm,
        @Positive Integer tempoEstimadoMinutos,
        String rotaResumo,
        String observacoes
) {
}
