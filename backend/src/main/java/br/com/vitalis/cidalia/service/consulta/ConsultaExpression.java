package br.com.vitalis.cidalia.service.consulta;

import br.com.vitalis.cidalia.domain.Ocorrencia;

public interface ConsultaExpression {
    // Padrao Interpreter: cada expressao sabe avaliar uma ocorrencia.
    boolean matches(Ocorrencia ocorrencia);

    String describe();
}
