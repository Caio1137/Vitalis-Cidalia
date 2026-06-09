package br.com.vitalis.cidalia.dto;

import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import java.time.LocalDateTime;

public record OcorrenciaResponse(
        Long id,
        String protocolo,
        String solicitanteNome,
        String solicitanteDocumento,
        String telefoneSolicitante,
        TipoOcorrencia tipo,
        PrioridadeOcorrencia prioridade,
        StatusOcorrencia status,
        String endereco,
        String bairro,
        String descricao,
        LocalDateTime registradaEm,
        PontoAtendimentoResponse pontoDestino
) {
}
