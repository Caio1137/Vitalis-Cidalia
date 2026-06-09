package br.com.vitalis.cidalia.validation;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;

class RegexPatternsTest {

    @Test
    void deveValidarPlacaMercosulNoFormatoDoProjeto() {
        assertThat(Pattern.matches(RegexPatterns.PLACA_MERCOSUL, "ABC1D23")).isTrue();
        assertThat(Pattern.matches(RegexPatterns.PLACA_MERCOSUL, "ABC1234")).isFalse();
    }

    @Test
    void deveValidarCpfOuCnpjComOuSemPontuacao() {
        assertThat(Pattern.matches(RegexPatterns.CPF_CNPJ, "123.456.789-09")).isTrue();
        assertThat(Pattern.matches(RegexPatterns.CPF_CNPJ, "12345678909")).isTrue();
        assertThat(Pattern.matches(RegexPatterns.CPF_CNPJ, "12.345.678/0001-90")).isTrue();
        assertThat(Pattern.matches(RegexPatterns.CPF_CNPJ, "12345")).isFalse();
    }
}
