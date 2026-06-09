package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.domain.TipoVeiculo;

public record VeiculoResponse(
        Long id,
        String prefixo,
        String placa,
        TipoVeiculo tipo,
        StatusOperacional status,
        String observacao
) {
}
