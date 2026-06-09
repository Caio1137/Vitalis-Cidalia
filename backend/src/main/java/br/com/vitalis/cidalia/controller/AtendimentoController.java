package br.com.vitalis.cidalia.controller;

import br.com.vitalis.cidalia.dto.AtendimentoRequest;
import br.com.vitalis.cidalia.dto.AtendimentoResponse;
import br.com.vitalis.cidalia.dto.AtualizarAtendimentoRequest;
import br.com.vitalis.cidalia.service.AtendimentoService;
import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/atendimentos")
public class AtendimentoController {

    private final AtendimentoService service;

    public AtendimentoController(AtendimentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AtendimentoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<AtendimentoResponse> criar(@Valid @RequestBody AtendimentoRequest request) {
        var response = service.criar(request);
        return ResponseEntity.created(URI.create("/api/atendimentos/" + response.id())).body(response);
    }

    @PatchMapping("/{id}")
    public AtendimentoResponse atualizar(@PathVariable Long id, @Valid @RequestBody AtualizarAtendimentoRequest request) {
        return service.atualizar(id, request);
    }
}
