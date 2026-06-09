package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.domain.TipoVeiculo;
import br.com.vitalis.cidalia.validation.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record VeiculoRequest(
        @NotBlank String prefixo,
        @NotBlank @Pattern(regexp = RegexPatterns.PLACA_MERCOSUL, message = "Placa deve seguir AAA1A11") String placa,
        @NotNull TipoVeiculo tipo,
        StatusOperacional status,
        String observacao
) {
}
