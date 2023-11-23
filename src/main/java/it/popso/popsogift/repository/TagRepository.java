package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface TagRepository extends JpaRepository<Tag,Integer> {

    @Query(value = "SELECT COUNT(ndg) FROM tag t join rel_beneficiario_tag rbt on t.id_tag = rbt.id_tag WHERE t.id_tag = ?1", nativeQuery = true)
    Integer findNumeroBeneficiari(@Param("idTag") int idTag);

    @Query(value = "SELECT COUNT(id_oggetto) FROM tag t join rel_oggetto_tag rot on t.id_tag = rot.id_tag WHERE t.id_tag = ?1", nativeQuery = true)
    Integer findNumeroOmaggi(@Param("idTag") int idTag);
}
