package br.com.vitalis.cidalia.repository;

import br.com.vitalis.cidalia.domain.StatusOperacional;
import br.com.vitalis.cidalia.domain.Veiculo;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {
    boolean existsByPlacaIgnoreCase(String placa);

    long countByStatus(StatusOperacional status);

    List<Veiculo> findByStatusOrderByPrefixoAsc(StatusOperacional status);
}
