package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusOcorrenciaRequest(@NotNull StatusOcorrencia status) {
}
