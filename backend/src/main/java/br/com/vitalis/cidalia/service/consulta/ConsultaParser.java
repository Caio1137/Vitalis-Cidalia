package br.com.vitalis.cidalia.service.consulta;

import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ConsultaParser {

    public ConsultaExpression analisar(List<Token> tokens) {
        var cursor = new Cursor(tokens);
        ConsultaExpression expression = consulta(cursor);
        consumir(cursor, TokenType.EOF, "Fim inesperado da consulta avancada.");
        return expression;
    }

    private ConsultaExpression consulta(Cursor cursor) {
        ConsultaExpression expression = condicao(cursor);
        while (match(cursor, TokenType.AND)) {
            expression = new AndExpression(expression, condicao(cursor));
        }
        return expression;
    }

    private ConsultaExpression condicao(Cursor cursor) {
        String parametro = consumir(cursor, TokenType.IDENTIFICADOR, "Esperado identificador antes do ponto.").lexeme();
        consumir(cursor, TokenType.PONTO, "Esperado ponto entre parametro e campo.");
        String campo = consumir(cursor, TokenType.IDENTIFICADOR, "Esperado nome do campo.").lexeme();
        String operador;
        if (match(cursor, TokenType.IGUAL)) {
            operador = "=";
        } else if (match(cursor, TokenType.DIFERENTE)) {
            operador = "!=";
        } else {
            throw new IllegalArgumentException("Esperado operador = ou !=.");
        }
        String valor = consumir(cursor, TokenType.STRING, "Esperado valor textual entre aspas.").lexeme();
        return new ConditionExpression(parametro, campo, operador, valor);
    }

    private boolean match(Cursor cursor, TokenType type) {
        if (check(cursor, type)) {
            cursor.atual++;
            return true;
        }
        return false;
    }

    private Token consumir(Cursor cursor, TokenType type, String message) {
        if (check(cursor, type)) {
            return cursor.tokens.get(cursor.atual++);
        }
        throw new IllegalArgumentException(message);
    }

    private boolean check(Cursor cursor, TokenType type) {
        return cursor.tokens.get(cursor.atual).type() == type;
    }

    private static final class Cursor {
        private final List<Token> tokens;
        private int atual;

        private Cursor(List<Token> tokens) {
            this.tokens = tokens;
        }
    }
}
