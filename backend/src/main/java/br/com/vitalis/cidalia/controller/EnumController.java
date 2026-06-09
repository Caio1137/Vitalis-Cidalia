package br.com.vitalis.cidalia.controller;

import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.StatusAtendimento;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import br.com.vitalis.cidalia.domain.TipoPontoAtendimento;
import br.com.vitalis.cidalia.domain.TipoVeiculo;
import java.util.Map;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/enums")
public class EnumController {

    @GetMapping
    public Map<String, Object> listar() {
        return Map.of(
                "prioridades", PrioridadeOcorrencia.values(),
                "statusOcorrencia", StatusOcorrencia.values(),
                "statusAtendimento", StatusAtendimento.values(),
                "statusOperacional", StatusOperacional.values(),
                "tiposOcorrencia", TipoOcorrencia.values(),
                "tiposPontoAtendimento", TipoPontoAtendimento.values(),
                "tiposVeiculo", TipoVeiculo.values()
        );
    }
}
