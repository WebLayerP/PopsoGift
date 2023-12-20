package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Stato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface StatoRepository extends JpaRepository<Stato,Integer> {

}
