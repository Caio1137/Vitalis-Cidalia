package br.com.vitalis.cidalia.service;

import br.com.vitalis.cidalia.dto.ConsultaAvancadaResponse;
import br.com.vitalis.cidalia.mapper.OcorrenciaMapper;
import br.com.vitalis.cidalia.service.consulta.ConsultaLexer;
import br.com.vitalis.cidalia.service.consulta.ConsultaParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ConsultaAvancadaService {

    private final ConsultaLexer lexer;
    private final ConsultaParser parser;
    private final OcorrenciaService ocorrenciaService;
    private final OcorrenciaMapper mapper;

    public ConsultaAvancadaService(ConsultaLexer lexer, ConsultaParser parser, OcorrenciaService ocorrenciaService, OcorrenciaMapper mapper) {
        this.lexer = lexer;
        this.parser = parser;
        this.ocorrenciaService = ocorrenciaService;
        this.mapper = mapper;
    }

    @Transactional(readOnly = true)
    public ConsultaAvancadaResponse executar(String comando) {
        var tokens = lexer.analisar(comando);
        var expression = parser.analisar(tokens);
        var resultados = ocorrenciaService.listarEntidades().stream()
                .filter(expression::matches)
                .map(mapper::toResponse)
                .toList();

        return new ConsultaAvancadaResponse(
                comando,
                tokens.stream().map(Object::toString).toList(),
                expression.describe(),
                resultados.size(),
                resultados
        );
    }
}
