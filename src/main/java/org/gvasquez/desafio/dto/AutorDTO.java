package org.gvasquez.desafio.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import org.gvasquez.desafio.model.Libro;

import java.util.ArrayList;
import java.util.List;

public record AutorDTO(
        Long id,
        String nombre,
        Integer fechaNacimiento,
        Integer fechaFallecimiento,
        String libros) {
}
