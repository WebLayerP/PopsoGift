package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Campagna;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Repository
@Transactional
public interface CampagnaRepository extends JpaRepository<Campagna, Integer> {

    @Query(value = "select * \n" +
            "from (select count(c.stato) stato_count, c.stato as id_st\n" +
            "from root.campagna c \n" +
            "       where (EXTRACT(YEAR FROM c.data_inizio_modifiche) = EXTRACT(YEAR FROM SYSDATE)\n" +
            "                OR EXTRACT(YEAR FROM c.data_fine_modifiche) = EXTRACT(YEAR FROM SYSDATE))\n" +
            "        GROUP by c.stato)\n" +
            "        RIGHT OUTER JOIN (select id_campagna as id_c, stato from ROOT.CAMPAGNA campagna\n" +
            "        where (EXTRACT(YEAR FROM campagna.data_inizio_modifiche) = EXTRACT(YEAR FROM SYSDATE)\n" +
            "                OR EXTRACT(YEAR FROM campagna.data_fine_modifiche) = EXTRACT(YEAR FROM SYSDATE)))\n" +
            "        on stato = id_st\n" +
            "     Left OUTER JOIN ROOT.SEGNALAZIONE\n" +
            "     on id_c = segnalazione.id_campagna", nativeQuery = true)
    List<Object[]> findAllCampagnaGroupByStato();
    @Query("SELECT MAX (c.dataAggiornamento) FROM Campagna c WHERE YEAR (c.dataAggiornamento)= :anno")
    Date findMaxDataAggiornamentoForYear(@Param("anno") Integer anno);

}

