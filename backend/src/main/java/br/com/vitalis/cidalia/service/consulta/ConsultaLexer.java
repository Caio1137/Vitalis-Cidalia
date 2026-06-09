package br.com.vitalis.cidalia.service.consulta;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class ConsultaLexer {

    public List<Token> analisar(String comando) {
        var tokens = new ArrayList<Token>();
        int atual = 0;

        while (atual < comando.length()) {
            char c = comando.charAt(atual);

            if (Character.isWhitespace(c)) {
                atual++;
            } else if (c == '.') {
                tokens.add(new Token(TokenType.PONTO, "."));
                atual++;
            } else if (c == '=') {
                tokens.add(new Token(TokenType.IGUAL, "="));
                atual++;
            } else if (c == '!' && atual + 1 < comando.length() && comando.charAt(atual + 1) == '=') {
                tokens.add(new Token(TokenType.DIFERENTE, "!="));
                atual += 2;
            } else if (c == '"') {
                int inicio = ++atual;
                while (atual < comando.length() && comando.charAt(atual) != '"') {
                    atual++;
                }
                if (atual >= comando.length()) {
                    throw new IllegalArgumentException("String sem aspas finais na consulta avancada.");
                }
                tokens.add(new Token(TokenType.STRING, comando.substring(inicio, atual)));
                atual++;
            } else if (Character.isLetter(c) || c == '_') {
                int inicio = atual;
                while (atual < comando.length()) {
                    char proximo = comando.charAt(atual);
                    if (!Character.isLetterOrDigit(proximo) && proximo != '_') {
                        break;
                    }
                    atual++;
                }
                String lexema = comando.substring(inicio, atual);
                tokens.add(new Token("AND".equalsIgnoreCase(lexema) ? TokenType.AND : TokenType.IDENTIFICADOR, lexema));
            } else {
                throw new IllegalArgumentException("Caractere invalido na consulta avancada: " + c);
            }
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }
}
