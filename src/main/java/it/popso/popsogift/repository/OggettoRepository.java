package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Oggetto;
import it.popso.popsogift.utils.Constants;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface OggettoRepository extends JpaRepository<Oggetto, Integer>{

     @Query("SELECT MAX(o.dataAggiornamento) FROM Oggetto o")
     Date findMaxByDataAggiornamento();
     @Query("select o.tipologia.idTipologia, count(o.idOggetto) as numero from Oggetto o group by o.tipologia.idTipologia")
     List<Object[]> numeroOggettiGroupByTipologia();
     @Query(value = Constants.OMAGGI_FILTRATI, nativeQuery = true)
     Page<Object[]> findByTipologiaFornitoreTag(@Param("tipologia") String tipologia, @Param("fornitore") String fornitore, @Param("tag") String tag,  Pageable pageable);
}
