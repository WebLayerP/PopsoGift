package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Fornitore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface FornitoreRepository extends JpaRepository <Fornitore, Integer> {

    @Query("select count(f.idFornitore) from Fornitore f")
    int findNumeroFornitoriTotali();

    @Query("SELECT f FROM Fornitore f WHERE (?1 IS NULL OR f.ragioneSociale = ?1) AND (?2 IS NULL OR f.partitaIva = ?2)")
    Page<Fornitore> findByRagioneSocialeAndPartitaIva(@Param("ragioneSociale") String ragioneSociale, @Param("partitaIva") String partitaIva, Pageable pageable);
}
