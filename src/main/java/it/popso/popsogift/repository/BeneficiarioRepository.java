package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Beneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface BeneficiarioRepository extends JpaRepository<Beneficiario, Integer> {

}
