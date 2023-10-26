package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Notifica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface NotificaRepository extends JpaRepository<Notifica, Integer> {

        List<Notifica> findByRuolo(String ruolo);
        List<Notifica> findByFilialeCodiceFilialeAndRuolo(String codFiliale, String ruolo);
}
