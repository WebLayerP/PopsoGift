package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Campagna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Repository
@Transactional
public interface CampagnaRepository extends JpaRepository<Campagna, Integer> {

    @Query("SELECT COUNT(c.idCampagna), s.idStato,c.dataAggiornamento, c.titoloCampagna, c.idCampagna FROM Campagna c JOIN c.stato s JOIN c.segnalazione st " +
            "WHERE YEAR(c.dataInizioModifiche) = YEAR(CURRENT_DATE) " +
            "OR YEAR(c.dataFineModifiche) = YEAR(CURRENT_DATE) " +
            "GROUP BY s.idStato,c.dataAggiornamento, c.titoloCampagna, c.idCampagna")
    List<Object[]> findAllCampagnaGroupByStatoSegn();

    @Query("select sub " +
            "from (select count(c.stato) as stato_count, c.stato as id_st " +
            "from Campagna c " +
            "where (YEAR(c.dataInizioModifiche) = YEAR(CURRENT_DATE) " +
            "OR YEAR(c.dataFineModifiche) = YEAR(CURRENT_DATE)) " +
            "GROUP by c.stato) as sub " +
            "RIGHT OUTER JOIN (select campagna.idCampagna as id_c, campagna.stato from Campagna campagna " +
            "where (YEAR(campagna.dataInizioModifiche) = YEAR(CURRENT_DATE) " +
            "OR YEAR(campagna.dataFineModifiche) = YEAR(CURRENT_DATE))) as dub " +
            "on campagna.stato = sub.id_st " +
            "LEFT OUTER JOIN Segnalazione segnalazione " +
            "on dub.id_c = segnalazione.idCampagna")
    List<Object[]> findAllCampagnaGroupByStato();

}

