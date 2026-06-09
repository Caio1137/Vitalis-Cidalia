package br.com.vitalis.cidalia.controller;

import br.com.vitalis.cidalia.dto.PontoAtendimentoRequest;
import br.com.vitalis.cidalia.dto.PontoAtendimentoResponse;
import br.com.vitalis.cidalia.service.PontoAtendimentoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pontos-atendimento")
public class PontoAtendimentoController {

    private final PontoAtendimentoService service;

    public PontoAtendimentoController(PontoAtendimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PontoAtendimentoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<PontoAtendimentoResponse> criar(@Valid @RequestBody PontoAtendimentoRequest request) {
        var response = service.criar(request);
        return ResponseEntity.created(URI.create("/api/pontos-atendimento/" + response.id())).body(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> inativar(@PathVariable Long id) {
        service.inativar(id);
        return ResponseEntity.noContent().build();
    }
}
