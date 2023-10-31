package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Campagna;
import it.popso.popsogift.utils.Constants;
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

    @Query(value= Constants.CAMPAGNA_OVERVIEW,nativeQuery = true)
    List<Object[]> findAllCampagnaByIdCampagna(@Param("idCampagna") int idCampagna);


    @Query(value= Constants.CAMPAGNE_OVERVIEW,nativeQuery = true)
    List<Object[]> findAllCampagnaGroupByStato();

    @Query(value= Constants.CAMPAGNE_OVERVIEW + Constants.CAMPAGNE_OVERVIEW_FILIALI,nativeQuery = true)
    List<Object[]> findAllCampagnaGroupByStatoFiliali(@Param("codiciFiliale") List<String> codiciFiliale);
    @Query("SELECT MAX (c.dataAggiornamento) FROM Campagna c WHERE YEAR (c.dataAggiornamento)= :anno")
    Date findMaxDataAggiornamentoForYear(@Param("anno") Integer anno);

}

