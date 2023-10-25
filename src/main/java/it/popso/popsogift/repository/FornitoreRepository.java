package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Fornitore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FornitoreRepository extends JpaRepository <Fornitore, Integer> {

    @Query("select count(f.idFornitore) from Fornitore f")
    int findNumeroFornitoriTorali();
}
