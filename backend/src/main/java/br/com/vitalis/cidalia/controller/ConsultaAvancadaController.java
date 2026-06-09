package br.com.vitalis.cidalia.controller;

import br.com.vitalis.cidalia.dto.ConsultaAvancadaRequest;
import br.com.vitalis.cidalia.dto.ConsultaAvancadaResponse;
import br.com.vitalis.cidalia.service.ConsultaAvancadaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/relatorios")
public class ConsultaAvancadaController {

    private final ConsultaAvancadaService service;

    public ConsultaAvancadaController(ConsultaAvancadaService service) {
        this.service = service;
    }

    @PostMapping("/consulta-avancada")
    public ConsultaAvancadaResponse consultar(@Valid @RequestBody ConsultaAvancadaRequest request) {
        return service.executar(request.comando());
    }
}
