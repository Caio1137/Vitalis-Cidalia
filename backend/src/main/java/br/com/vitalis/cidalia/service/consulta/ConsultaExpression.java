package br.com.vitalis.cidalia.service.consulta;

import br.com.vitalis.cidalia.domain.Ocorrencia;

public interface ConsultaExpression {
    boolean matches(Ocorrencia ocorrencia);

    String describe();
}
