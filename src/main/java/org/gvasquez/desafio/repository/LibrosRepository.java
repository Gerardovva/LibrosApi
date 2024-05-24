package org.gvasquez.desafio.repository;

import org.gvasquez.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libro,Long> {

   @Query("select l from Libro l where idiomas = :idioma")
   List<Libro> listarPorIdioma(String idioma);


}
