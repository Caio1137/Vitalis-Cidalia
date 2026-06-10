package br.com.vitalis.cidalia.service.consulta;

import br.com.vitalis.cidalia.domain.Ocorrencia;

public class AndExpression implements ConsultaExpression {

    // Padrao Composite: combina duas expressoes, que podem ser folhas ou outras composicoes.
    private final ConsultaExpression esquerda;
    private final ConsultaExpression direita;

    public AndExpression(ConsultaExpression esquerda, ConsultaExpression direita) {
        this.esquerda = esquerda;
        this.direita = direita;
    }

    @Override
    public boolean matches(Ocorrencia ocorrencia) {
        return esquerda.matches(ocorrencia) && direita.matches(ocorrencia);
    }

    @Override
    public String describe() {
        return "AND(" + esquerda.describe() + ", " + direita.describe() + ")";
    }
}
