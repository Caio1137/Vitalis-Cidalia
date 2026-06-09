package br.com.vitalis.cidalia.service.consulta;

import br.com.vitalis.cidalia.domain.Ocorrencia;
import java.util.Locale;
import java.util.Objects;

public class ConditionExpression implements ConsultaExpression {

    private final String parametro;
    private final String campo;
    private final String operador;
    private final String valorEsperado;

    public ConditionExpression(String parametro, String campo, String operador, String valorEsperado) {
        this.parametro = parametro;
        this.campo = campo;
        this.operador = operador;
        this.valorEsperado = valorEsperado;
    }

    @Override
    public boolean matches(Ocorrencia ocorrencia) {
        String valorAtual = resolverValor(ocorrencia);
        boolean iguais = Objects.equals(normalizar(valorAtual), normalizar(valorEsperado));
        return "=".equals(operador) ? iguais : !iguais;
    }

    @Override
    public String describe() {
        return "(" + parametro + "." + campo + " " + operador + " \"" + valorEsperado + "\")";
    }

    private String resolverValor(Ocorrencia ocorrencia) {
        String raiz = parametro.toLowerCase(Locale.ROOT);
        if ("parametro".equals(raiz)) {
            raiz = "ocorrencia";
        }

        String chave = raiz + "." + campo.toLowerCase(Locale.ROOT);
        return switch (chave) {
            case "ocorrencia.tipo" -> ocorrencia.getTipo().name();
            case "ocorrencia.status" -> ocorrencia.getStatus().name();
            case "ocorrencia.prioridade" -> ocorrencia.getPrioridade().name();
            case "ocorrencia.setor", "ponto.setor" -> ocorrencia.getPontoDestino().getSetor();
            case "ocorrencia.bairro" -> ocorrencia.getBairro();
            case "ponto.tipo" -> ocorrencia.getPontoDestino().getTipo().name();
            case "atendimento.status" -> ocorrencia.getAtendimento() == null ? "" : ocorrencia.getAtendimento().getStatus().name();
            default -> throw new IllegalArgumentException("Campo nao permitido na consulta avancada: " + parametro + "." + campo);
        };
    }

    private String normalizar(String valor) {
        return valor == null ? "" : valor.trim().toUpperCase(Locale.ROOT);
    }
}
