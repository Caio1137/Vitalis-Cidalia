package br.com.vitalis.cidalia.mapper;

import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.domain.Veiculo;
import br.com.vitalis.cidalia.dto.VeiculoRequest;
import br.com.vitalis.cidalia.dto.VeiculoResponse;
import org.springframework.stereotype.Component;

@Component
public class VeiculoMapper {

    public Veiculo toEntity(VeiculoRequest request) {
        var veiculo = new Veiculo();
        veiculo.setPrefixo(request.prefixo());
        veiculo.setPlaca(request.placa().toUpperCase());
        veiculo.setTipo(request.tipo());
        veiculo.setStatus(request.status() == null ? StatusOperacional.DISPONIVEL : request.status());
        veiculo.setObservacao(request.observacao());
        return veiculo;
    }

    public VeiculoResponse toResponse(Veiculo veiculo) {
        return new VeiculoResponse(
                veiculo.getId(),
                veiculo.getPrefixo(),
                veiculo.getPlaca(),
                veiculo.getTipo(),
                veiculo.getStatus(),
                veiculo.getObservacao()
        );
    }
}
