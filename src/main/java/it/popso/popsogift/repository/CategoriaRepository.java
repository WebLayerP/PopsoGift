package it.popso.popsogift.repository;

import it.popso.popsogift.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria,Integer> {

    boolean existsByNomeCategoria(String nomeCategoria);
}
