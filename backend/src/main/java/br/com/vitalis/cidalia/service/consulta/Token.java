package br.com.vitalis.cidalia.service.consulta;

public record Token(TokenType type, String lexeme) {
    @Override
    public String toString() {
        return type + "(" + lexeme + ")";
    }
}
