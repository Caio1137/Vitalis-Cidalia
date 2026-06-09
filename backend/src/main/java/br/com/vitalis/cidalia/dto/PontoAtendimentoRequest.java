package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.TipoPontoAtendimento;
import br.com.vitalis.cidalia.validation.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import java.math.BigDecimal;

public record PontoAtendimentoRequest(
        @NotBlank String nome,
        @NotNull TipoPontoAtendimento tipo,
        @NotBlank String setor,
        @NotBlank String endereco,
        @Pattern(regexp = RegexPatterns.CEP, message = "CEP deve seguir o formato 00000-000") String cep,
        @Pattern(regexp = RegexPatterns.TELEFONE_BR, message = "Telefone deve seguir o padrao brasileiro") String telefone,
        BigDecimal latitude,
        BigDecimal longitude
) {
}
