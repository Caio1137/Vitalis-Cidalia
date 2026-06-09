package br.com.vitalis.cidalia.dto;

import java.util.List;

public record ConsultaAvancadaResponse(
        String comando,
        List<String> tokens,
        String arvoreSintatica,
        long totalEncontrado,
        List<OcorrenciaResponse> resultados
) {
}
