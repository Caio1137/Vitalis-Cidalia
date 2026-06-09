package br.com.vitalis.cidalia.repository;

import br.com.vitalis.cidalia.domain.Equipe;
import br.com.vitalis.cidalia.domain.StatusOperacional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EquipeRepository extends JpaRepository<Equipe, Long> {
    boolean existsByNomeIgnoreCase(String nome);

    long countByStatus(StatusOperacional status);

    List<Equipe> findByStatusOrderByNomeAsc(StatusOperacional status);
}
