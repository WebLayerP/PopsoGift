package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag,Integer> {

    @Query(value = "SELECT COUNT(t) FROM Beneficiario b join b.tag t WHERE t.idTag = ?1")
    Integer findNumeroBeneficiari(@Param("idTag") int idTag);

    @Query(value =  "SELECT COUNT(t) FROM Oggetto o join o.tag t WHERE t.idTag = ?1")
    Integer findNumeroOmaggi(@Param("idTag") int idTag);

    Page<Tag> findByStatoCancellazione(Boolean stato, Pageable pageable);
    List<Tag> findByNomeTagContainingAndStatoCancellazione(String nomeTag, Boolean stato);

    Optional<Tag> findByIdTagAndStatoCancellazione(@Param("idTag") Integer id, @Param("statoCancellazione") Boolean stato);
}
