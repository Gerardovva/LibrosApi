package org.gvasquez.desafio.repository;

import org.gvasquez.desafio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibrosRepository extends JpaRepository<Libro,Long> {

}
