package br.com.vitalis.cidalia.controller;

import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import br.com.vitalis.cidalia.dto.AtualizarStatusOcorrenciaRequest;
import br.com.vitalis.cidalia.dto.OcorrenciaRequest;
import br.com.vitalis.cidalia.dto.OcorrenciaResponse;
import br.com.vitalis.cidalia.service.OcorrenciaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/ocorrencias")
public class OcorrenciaController {

    private final OcorrenciaService service;

    public OcorrenciaController(OcorrenciaService service) {
        this.service = service;
    }

    @GetMapping
    public List<OcorrenciaResponse> listar(
            @RequestParam(required = false) StatusOcorrencia status,
            @RequestParam(required = false) PrioridadeOcorrencia prioridade,
            @RequestParam(required = false) TipoOcorrencia tipo,
            @RequestParam(required = false) String setor
    ) {
        return service.listar(status, prioridade, tipo, setor);
    }

    @PostMapping
    public ResponseEntity<OcorrenciaResponse> criar(@Valid @RequestBody OcorrenciaRequest request) {
        var response = service.criar(request);
        return ResponseEntity.created(URI.create("/api/ocorrencias/" + response.id())).body(response);
    }

    @PatchMapping("/{id}/status")
    public OcorrenciaResponse atualizarStatus(@PathVariable Long id, @Valid @RequestBody AtualizarStatusOcorrenciaRequest request) {
        return service.atualizarStatus(id, request.status());
    }
}
