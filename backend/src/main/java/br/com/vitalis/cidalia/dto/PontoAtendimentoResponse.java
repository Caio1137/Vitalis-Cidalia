package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.TipoPontoAtendimento;
import java.math.BigDecimal;

public record PontoAtendimentoResponse(
        Long id,
        String nome,
        TipoPontoAtendimento tipo,
        String setor,
        String endereco,
        String cep,
        String telefone,
        BigDecimal latitude,
        BigDecimal longitude,
        boolean ativo
) {
}
