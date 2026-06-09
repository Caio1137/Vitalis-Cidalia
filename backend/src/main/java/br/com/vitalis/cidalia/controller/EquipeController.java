package br.com.vitalis.cidalia.controller;

import br.com.vitalis.cidalia.dto.AtualizarStatusOperacionalRequest;
import br.com.vitalis.cidalia.dto.EquipeRequest;
import br.com.vitalis.cidalia.dto.EquipeResponse;
import br.com.vitalis.cidalia.service.EquipeService;
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
@RequestMapping("/api/equipes")
public class EquipeController {

    private final EquipeService service;

    public EquipeController(EquipeService service) {
        this.service = service;
    }

    @GetMapping
    public List<EquipeResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<EquipeResponse> criar(@Valid @RequestBody EquipeRequest request) {
        var response = service.criar(request);
        return ResponseEntity.created(URI.create("/api/equipes/" + response.id())).body(response);
    }

    @PatchMapping("/{id}/status")
    public EquipeResponse atualizarStatus(@PathVariable Long id, @Valid @RequestBody AtualizarStatusOperacionalRequest request) {
        return service.atualizarStatus(id, request.status());
    }
}
