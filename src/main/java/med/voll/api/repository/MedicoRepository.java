package med.voll.api.repository;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    @Query("""
            select
                m
            from
                Medico m
                left join Consulta c on c.medico = m
            where
                m.ativo = true
                and m.especialidade = :especialidade
                and c.data <> :data
                and c.motivoCancelamento is null
            order by
                rand()
            limit 1
            """)
    Medico findFirstRandomByEspecialidadeAndFreeData(Especialidade especialidade, LocalDateTime data);

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select
                m.ativo
            from
                Medico m
            where
                m.id = :id
            """)
    boolean findAtivoById(Long id);
}
