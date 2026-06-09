package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import br.com.vitalis.cidalia.validation.RegexPatterns;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record OcorrenciaRequest(
        @NotBlank String solicitanteNome,
        @Pattern(regexp = RegexPatterns.CPF_CNPJ, message = "Documento deve ser CPF ou CNPJ valido em formato") String solicitanteDocumento,
        @Pattern(regexp = RegexPatterns.TELEFONE_BR, message = "Telefone deve seguir o padrao brasileiro") String telefoneSolicitante,
        @NotNull TipoOcorrencia tipo,
        @NotNull PrioridadeOcorrencia prioridade,
        @NotBlank String endereco,
        @NotBlank String bairro,
        @NotBlank @Size(min = 10, max = 1200) String descricao,
        @NotNull Long pontoDestinoId
) {
}
