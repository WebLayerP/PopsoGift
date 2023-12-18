package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {
    @Query("SELECT COUNT(b), SUM (CASE WHEN b.privacy = false THEN 1 ELSE 0 END), MAX(b.dataAggiornamento) FROM Beneficiario b")
    List<Object[]> countBeneficiariAndPrivacy();
    Beneficiario findByNdgAndStatoCancellazione(@Param("ndg") String ndg, @Param("statoCancellazione") Boolean stato);

    @Query(value="SELECT id_oggetto FROM REL_BENEFICIARIO_OGGETTO_CAMPAGNA WHERE ID_CAMPAGNA = :idCampagna AND NDG = :ndg",nativeQuery = true)
    List<Object[]> findByIdCampagnaAndNdg(@Param("idCampagna") Integer idCampagna, @Param("ndg") String ndg);

    @Query(value="SELECT id_oggetto FROM REL_BENEFICIARIO_OGGETTO_CAMPAGNA WHERE ID_CAMPAGNA <> :idCampagna AND NDG = :ndg",nativeQuery = true)
    List<Object[]> findByNdgExcludeCampagna(@Param("idCampagna") Integer idCampagna, @Param("ndg") String ndg);

}
