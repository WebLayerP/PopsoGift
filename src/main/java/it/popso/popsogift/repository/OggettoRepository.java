package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Oggetto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

}
