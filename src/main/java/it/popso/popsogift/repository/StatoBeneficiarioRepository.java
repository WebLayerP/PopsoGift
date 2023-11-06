package it.popso.popsogift.repository;

import it.popso.popsogift.entity.StatoBeneficiario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatoBeneficiarioRepository extends JpaRepository <StatoBeneficiario,Integer> {
}
