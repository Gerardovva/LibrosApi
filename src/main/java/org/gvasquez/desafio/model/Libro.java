package org.gvasquez.desafio.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @Transient
    private List<DatosAutor> autor;
    @Enumerated(EnumType.STRING)
    private Idiomas idiomas;
    private Double numeroDeDescargas;
}
