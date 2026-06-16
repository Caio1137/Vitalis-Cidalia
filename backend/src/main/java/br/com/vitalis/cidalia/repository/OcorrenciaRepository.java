package br.com.vitalis.cidalia.repository;

import br.com.vitalis.cidalia.domain.Ocorrencia;
import br.com.vitalis.cidalia.domain.PrioridadeOcorrencia;
import br.com.vitalis.cidalia.domain.StatusOcorrencia;
import br.com.vitalis.cidalia.domain.TipoOcorrencia;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface OcorrenciaRepository extends JpaRepository<Ocorrencia, Long> {
    boolean existsByProtocolo(String protocolo);

    long countByStatusNotIn(List<StatusOcorrencia> status);

    long countByPrioridadeAndStatusNotIn(PrioridadeOcorrencia prioridade, List<StatusOcorrencia> status);

    List<Ocorrencia> findTop5ByOrderByRegistradaEmDesc();

    @Query("""
            select o from Ocorrencia o
            join o.pontoDestino p
            where (:status is null or o.status = :status)
              and (:prioridade is null or o.prioridade = :prioridade)
              and (:tipo is null or o.tipo = :tipo)
              and (:setor is null or lower(p.setor) = :setor)
            order by o.registradaEm desc
            """)
    List<Ocorrencia> filtrar(
            @Param("status") StatusOcorrencia status,
            @Param("prioridade") PrioridadeOcorrencia prioridade,
            @Param("tipo") TipoOcorrencia tipo,
            @Param("setor") String setor
    );
}
