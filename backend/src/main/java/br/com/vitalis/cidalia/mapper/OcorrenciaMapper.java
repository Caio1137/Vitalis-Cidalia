package br.com.vitalis.cidalia.mapper;

import br.com.vitalis.cidalia.domain.Ocorrencia;
import br.com.vitalis.cidalia.dto.OcorrenciaRequest;
import br.com.vitalis.cidalia.dto.OcorrenciaResponse;
import org.springframework.stereotype.Component;

@Component
public class OcorrenciaMapper {

    private final PontoAtendimentoMapper pontoMapper;

    public OcorrenciaMapper(PontoAtendimentoMapper pontoMapper) {
        this.pontoMapper = pontoMapper;
    }

    public Ocorrencia toEntity(OcorrenciaRequest request) {
        var ocorrencia = new Ocorrencia();
        ocorrencia.setSolicitanteNome(request.solicitanteNome());
        ocorrencia.setSolicitanteDocumento(request.solicitanteDocumento());
        ocorrencia.setTelefoneSolicitante(request.telefoneSolicitante());
        ocorrencia.setTipo(request.tipo());
        ocorrencia.setPrioridade(request.prioridade());
        ocorrencia.setEndereco(request.endereco());
        ocorrencia.setBairro(request.bairro());
        ocorrencia.setDescricao(request.descricao());
        return ocorrencia;
    }

    public OcorrenciaResponse toResponse(Ocorrencia ocorrencia) {
        return new OcorrenciaResponse(
                ocorrencia.getId(),
                ocorrencia.getProtocolo(),
                ocorrencia.getSolicitanteNome(),
                ocorrencia.getSolicitanteDocumento(),
                ocorrencia.getTelefoneSolicitante(),
                ocorrencia.getTipo(),
                ocorrencia.getPrioridade(),
                ocorrencia.getStatus(),
                ocorrencia.getEndereco(),
                ocorrencia.getBairro(),
                ocorrencia.getDescricao(),
                ocorrencia.getRegistradaEm(),
                pontoMapper.toResponse(ocorrencia.getPontoDestino())
        );
    }
}
