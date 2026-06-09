package br.com.vitalis.cidalia.repository;

import br.com.vitalis.cidalia.domain.PontoAtendimento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PontoAtendimentoRepository extends JpaRepository<PontoAtendimento, Long> {
    boolean existsByNomeIgnoreCase(String nome);

    List<PontoAtendimento> findByAtivoTrueOrderByNomeAsc();
}
