package med.voll.api.repository;

import med.voll.api.model.Medico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long>, PagingAndSortingRepository<Medico, Long> {

    Page<Medico> findAllByAtivoTrue(Pageable pageable);
}
