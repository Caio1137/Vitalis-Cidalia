package br.com.vitalis.cidalia.repository;

import br.com.vitalis.cidalia.domain.Atendimento;
import br.com.vitalis.cidalia.domain.StatusAtendimento;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AtendimentoRepository extends JpaRepository<Atendimento, Long> {
    boolean existsByCodigo(String codigo);

    boolean existsByOcorrenciaId(Long ocorrenciaId);

    long countByStatusIn(List<StatusAtendimento> status);

    List<Atendimento> findTop5ByOrderByDespachoEmDesc();
}
