package br.com.vitalis.cidalia.service;

import java.time.Year;
import org.springframework.stereotype.Component;

@Component
public class CodigoOperacionalFactory {

    // Padrao Factory Method: centraliza a criacao dos identificadores operacionais do dominio.
    public String criarProtocoloOcorrencia(long sequencial) {
        return "CID-%d-%06d".formatted(Year.now().getValue(), sequencial);
    }

    public String criarCodigoAtendimento(long sequencial) {
        return "ATD-%06d".formatted(sequencial);
    }
}
