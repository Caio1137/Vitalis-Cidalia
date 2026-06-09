package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.StatusOperacional;

public record EquipeResponse(
        Long id,
        String nome,
        String setor,
        String telefone,
        StatusOperacional status,
        VeiculoResponse veiculo
) {
}
