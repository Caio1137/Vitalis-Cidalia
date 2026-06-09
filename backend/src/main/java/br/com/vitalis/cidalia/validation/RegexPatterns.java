package br.com.vitalis.cidalia.validation;

public final class RegexPatterns {

    // LFA: ER para placa Mercosul no formato solicitado pelo PI: AAA1A11.
    public static final String PLACA_MERCOSUL = "^[A-Z]{3}[0-9][A-Z][0-9]{2}$";

    // LFA: aceita CPF ou CNPJ com ou sem pontuacao.
    public static final String CPF_CNPJ = "^(\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}|\\d{2}\\.?\\d{3}\\.?\\d{3}/?\\d{4}-?\\d{2})$";

    public static final String TELEFONE_BR = "^\\(?\\d{2}\\)?\\s?9?\\d{4}-?\\d{4}$";
    public static final String CEP = "^\\d{5}-?\\d{3}$";
    public static final String PROTOCOLO_OCORRENCIA = "^CID-\\d{4}-\\d{6}$";
    public static final String CODIGO_ATENDIMENTO = "^ATD-\\d{6}$";

    private RegexPatterns() {
    }
}
