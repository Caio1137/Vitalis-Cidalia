package br.com.vitalis.cidalia.controller;

import br.com.vitalis.cidalia.dto.AtualizarStatusOperacionalRequest;
import br.com.vitalis.cidalia.dto.VeiculoRequest;
import br.com.vitalis.cidalia.dto.VeiculoResponse;
import br.com.vitalis.cidalia.service.VeiculoService;
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
@RequestMapping("/api/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @GetMapping
    public List<VeiculoResponse> listar() {
        return service.listar();
    }

    @PostMapping
    public ResponseEntity<VeiculoResponse> criar(@Valid @RequestBody VeiculoRequest request) {
        var response = service.criar(request);
        return ResponseEntity.created(URI.create("/api/veiculos/" + response.id())).body(response);
    }

    @PatchMapping("/{id}/status")
    public VeiculoResponse atualizarStatus(@PathVariable Long id, @Valid @RequestBody AtualizarStatusOperacionalRequest request) {
        return service.atualizarStatus(id, request.status());
    }
}
