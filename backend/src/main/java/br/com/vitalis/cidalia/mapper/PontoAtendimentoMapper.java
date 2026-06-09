package br.com.vitalis.cidalia.mapper;

import br.com.vitalis.cidalia.domain.PontoAtendimento;
import br.com.vitalis.cidalia.dto.PontoAtendimentoRequest;
import br.com.vitalis.cidalia.dto.PontoAtendimentoResponse;
import org.springframework.stereotype.Component;

@Component
public class PontoAtendimentoMapper {

    public PontoAtendimento toEntity(PontoAtendimentoRequest request) {
        var ponto = new PontoAtendimento();
        ponto.setNome(request.nome());
        ponto.setTipo(request.tipo());
        ponto.setSetor(request.setor());
        ponto.setEndereco(request.endereco());
        ponto.setCep(request.cep());
        ponto.setTelefone(request.telefone());
        ponto.setLatitude(request.latitude());
        ponto.setLongitude(request.longitude());
        return ponto;
    }

    public PontoAtendimentoResponse toResponse(PontoAtendimento ponto) {
        return new PontoAtendimentoResponse(
                ponto.getId(),
                ponto.getNome(),
                ponto.getTipo(),
                ponto.getSetor(),
                ponto.getEndereco(),
                ponto.getCep(),
                ponto.getTelefone(),
                ponto.getLatitude(),
                ponto.getLongitude(),
                ponto.isAtivo()
        );
    }
}
