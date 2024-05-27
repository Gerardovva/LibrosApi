package org.gvasquez.desafio.dto;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import org.gvasquez.desafio.model.Autor;

public record LibroDTO(Long id,
                       String titulo,
                       String idiomas,
                       Double numeroDeDescargas,
                       Autor autor) {

}
