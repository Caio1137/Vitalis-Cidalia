package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusOperacional;
import jakarta.validation.constraints.NotNull;

public record AtualizarStatusOperacionalRequest(@NotNull StatusOperacional status) {
}
