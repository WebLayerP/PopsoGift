package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Filiale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FilialeRepository extends JpaRepository<Filiale, String> {

}
