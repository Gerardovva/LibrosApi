package org.gvasquez.desafio.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * La clase Autor representa la entidad Autor en la base de datos.
 * Cada instancia de esta clase corresponde a una fila en la tabla "autor".
 */
@Entity
@Table(name = "autor")
public class Autor {
    //atributos
    // Clave primaria
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // Nombre del autor
    private String nombre;
    // Fecha de nacimiento del autor
    private Integer fechaNacimiento;
    // Fecha de fallecimiento del autor
    private Integer fechaFallecimiento;

    // Lista de libros escritos por el autor
    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonManagedReference // Indica a Jackson que debe serializar esta lista y omitir el campo correspondiente en la clase Libro
    @JsonIgnore // Ignora esta propiedad durante la serialización y deserialización
    private List<Libro> libros = new ArrayList<>();


    //constructor
    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = Integer.valueOf(datosAutor.fechaNacimiento());
        this.fechaFallecimiento = Integer.valueOf(datosAutor.fechaFallecimiento());
    }

    //getter and setter


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    @Override
    public String toString() {
        return """
                Autor: %s
                Fecha de nacimeinto: %s
                Fecha de muerte: %s
                Libros : %s
                """.formatted(this.nombre, this.fechaNacimiento,
                this.fechaFallecimiento,
                this.libros.stream().map(Libro::getTitulo).collect(Collectors.toList()));

    }
}
