package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.validation.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record EquipeRequest(
        @NotBlank String nome,
        @NotBlank String setor,
        @Pattern(regexp = RegexPatterns.TELEFONE_BR, message = "Telefone deve seguir o padrao brasileiro") String telefone,
        StatusOperacional status,
        Long veiculoId
) {
}
