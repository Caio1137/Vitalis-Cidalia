package br.com.vitalis.cidalia.service.consulta;

import br.com.vitalis.cidalia.domain.Ocorrencia;

// Padrao Composite: condicoes simples e combinacoes logicas compartilham o mesmo contrato.
public interface ConsultaExpression {
    boolean matches(Ocorrencia ocorrencia);

    String describe();
}
