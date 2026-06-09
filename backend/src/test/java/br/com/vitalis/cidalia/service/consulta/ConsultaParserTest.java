package br.com.vitalis.cidalia.service.consulta;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import br.com.vitalis.cidalia.domain.Ocorrencia;
import br.com.vitalis.cidalia.domain.PontoAtendimento;
import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import br.com.vitalis.cidalia.domain.TipoPontoAtendimento;
import org.junit.jupiter.api.Test;

class ConsultaParserTest {

    private final ConsultaLexer lexer = new ConsultaLexer();
    private final ConsultaParser parser = new ConsultaParser();

    @Test
    void deveAnalisarConsultaComAnd() {
        var tokens = lexer.analisar("parametro.tipo = \"CARDIACA\" AND parametro.setor = \"NORTE\"");
        var expression = parser.analisar(tokens);

        assertThat(expression.matches(ocorrenciaValida())).isTrue();
        assertThat(expression.describe()).contains("AND");
    }

    @Test
    void deveRejeitarValorSemAspas() {
        var tokens = lexer.analisar("parametro.tipo = CARDIACA");
        assertThatThrownBy(() -> parser.analisar(tokens))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("valor textual entre aspas");
    }

    private Ocorrencia ocorrenciaValida() {
        var ponto = new PontoAtendimento();
        ponto.setNome("Hospital Municipal Norte");
        ponto.setTipo(TipoPontoAtendimento.HOSPITAL);
        ponto.setSetor("NORTE");
        ponto.setEndereco("Av. Saude");

        var ocorrencia = new Ocorrencia();
        ocorrencia.setSolicitanteNome("Marina Torres");
        ocorrencia.setTipo(TipoOcorrencia.CARDIACA);
        ocorrencia.setPrioridade(PrioridadeOcorrencia.CRITICA);
        ocorrencia.setEndereco("Rua 12");
        ocorrencia.setBairro("Setor Norte");
        ocorrencia.setDescricao("Paciente com dor toracica");
        ocorrencia.setPontoDestino(ponto);
        return ocorrencia;
    }
}
