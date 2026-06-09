package br.com.vitalis.cidalia.mapper;

import br.com.vitalis.cidalia.domain.Equipe;
import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.dto.EquipeRequest;
import br.com.vitalis.cidalia.dto.EquipeResponse;
import org.springframework.stereotype.Component;

@Component
public class EquipeMapper {

    private final VeiculoMapper veiculoMapper;

    public EquipeMapper(VeiculoMapper veiculoMapper) {
        this.veiculoMapper = veiculoMapper;
    }

    public Equipe toEntity(EquipeRequest request) {
        var equipe = new Equipe();
        equipe.setNome(request.nome());
        equipe.setSetor(request.setor());
        equipe.setTelefone(request.telefone());
        equipe.setStatus(request.status() == null ? StatusOperacional.DISPONIVEL : request.status());
        return equipe;
    }

    public EquipeResponse toResponse(Equipe equipe) {
        return new EquipeResponse(
                equipe.getId(),
                equipe.getNome(),
                equipe.getSetor(),
                equipe.getTelefone(),
                equipe.getStatus(),
                equipe.getVeiculo() == null ? null : veiculoMapper.toResponse(equipe.getVeiculo())
        );
    }
}
