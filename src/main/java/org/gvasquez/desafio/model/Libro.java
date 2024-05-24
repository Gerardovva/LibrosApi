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

    private String idiomas;
    private Double numeroDeDescargas;
    //@Transient
    @ManyToOne
    @JoinColumn(name = "autor_id",nullable = false)
    private Autor autor;

    public Libro() {
    }
    public Libro(DatosLibros datosLibros){
        this.titulo=datosLibros.titulo();
        this.idiomas= datosLibros.idiomas().get(0);
        this.numeroDeDescargas=datosLibros.numeroDeDescargas();
        this.autor=new Autor(datosLibros.autor().get(0));
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getIdiomas() {
        return idiomas;
    }

    public void setIdiomas(String idiomas) {
        this.idiomas = idiomas;
    }

    public Double getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Double numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    @Override
    public String toString() {
        return """
                ********** Libro **********
                Titulo: %s
                Autor: %s
                Idioma: %s
                Numero de descarga: %s
                ***************************
                """.formatted(this.titulo,this.autor.getNombre(),this.idiomas,this.numeroDeDescargas);
    }
}
