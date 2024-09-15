package med.voll.api.repository;

import med.voll.api.model.Especialidade;
import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);

    @Query("""
            select 
                m 
            from 
                Medico m
                left join Consulta c on m.id = c.medico_id
            where
                m.ativo = true
                and c.data <> :data
                and m.especialidade = :especialidade
            order by 
                rand()
            limit 1
            """)
    Medico findFirstRandomByEspecialidadeAndFreeData(Especialidade especialidade, LocalDateTime data);
}
