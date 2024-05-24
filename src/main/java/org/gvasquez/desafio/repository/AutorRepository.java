package org.gvasquez.desafio.repository;

import org.gvasquez.desafio.model.Autor;
import org.gvasquez.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    Autor findByNombreContainsIgnoreCase(String nombre);
}
