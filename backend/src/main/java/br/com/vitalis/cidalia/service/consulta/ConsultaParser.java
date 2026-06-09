package br.com.vitalis.cidalia.service.consulta;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ConsultaParser {

    private List<Token> tokens;
    private int atual;

    public ConsultaExpression analisar(List<Token> tokens) {
        this.tokens = tokens;
        this.atual = 0;
        ConsultaExpression expression = consulta();
        consumir(TokenType.EOF, "Fim inesperado da consulta avancada.");
        return expression;
    }

    private ConsultaExpression consulta() {
        ConsultaExpression expression = condicao();
        while (match(TokenType.AND)) {
            expression = new AndExpression(expression, condicao());
        }
        return expression;
    }

    private ConsultaExpression condicao() {
        String parametro = consumir(TokenType.IDENTIFICADOR, "Esperado identificador antes do ponto.").lexeme();
        consumir(TokenType.PONTO, "Esperado ponto entre parametro e campo.");
        String campo = consumir(TokenType.IDENTIFICADOR, "Esperado nome do campo.").lexeme();
        String operador;
        if (match(TokenType.IGUAL)) {
            operador = "=";
        } else if (match(TokenType.DIFERENTE)) {
            operador = "!=";
        } else {
            throw new IllegalArgumentException("Esperado operador = ou !=.");
        }
        String valor = consumir(TokenType.STRING, "Esperado valor textual entre aspas.").lexeme();
        return new ConditionExpression(parametro, campo, operador, valor);
    }

    private boolean match(TokenType type) {
        if (check(type)) {
            atual++;
            return true;
        }
        return false;
    }

    private Token consumir(TokenType type, String message) {
        if (check(type)) {
            return tokens.get(atual++);
        }
        throw new IllegalArgumentException(message);
    }

    private boolean check(TokenType type) {
        return tokens.get(atual).type() == type;
    }
}
