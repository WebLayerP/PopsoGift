package it.popso.popsogift.repository;

import it.popso.popsogift.entity.MatricoleLettura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface MatricoleRepository extends JpaRepository<MatricoleLettura, String> {
    List<MatricoleLettura> findByMatricolaAndNotificaIdNotifica(String matricola, int idNotifica);
}
