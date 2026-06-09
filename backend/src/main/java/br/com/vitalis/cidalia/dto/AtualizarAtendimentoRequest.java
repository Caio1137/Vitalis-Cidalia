package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusAtendimento;
import jakarta.validation.constraints.NotNull;

public record AtualizarAtendimentoRequest(
        @NotNull StatusAtendimento status,
        String observacoes
) {
}
