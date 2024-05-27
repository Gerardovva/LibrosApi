package org.gvasquez.desafio.repository;

import org.gvasquez.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LibrosRepository extends JpaRepository<Libro,Long> {



      @Query("SELECT l FROM Libro l WHERE l.idiomas = :idioma")
      List<Libro> listarPorIdioma(String idioma);

      @Query("SELECT l FROM Libro l")
      List<Libro> todosLosLibros();


}
