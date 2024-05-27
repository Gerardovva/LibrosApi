package org.gvasquez.desafio.principal;

import org.gvasquez.desafio.model.Autor;
import org.gvasquez.desafio.model.Datos;
import org.gvasquez.desafio.model.Libro;
import org.gvasquez.desafio.repository.AutorRepository;
import org.gvasquez.desafio.repository.LibrosRepository;
import org.gvasquez.desafio.service.ConsumoApi;
import org.gvasquez.desafio.service.ConvierteDatos;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvierteDatos conversor = new ConvierteDatos();
    private Scanner sc = new Scanner(System.in);
    private LibrosRepository repositorioLibro;
    private AutorRepository repositoryAutor;

    public Principal(LibrosRepository repository, AutorRepository repositoryAutor) {
        this.repositorioLibro = repository;
        this.repositoryAutor = repositoryAutor;
    }


    public void muestraMenu() {

        int opcion = -1;
        while (opcion != 0) {
            var menu = """
                    Elija la opción deseada
                    1.- Buscar por titulo
                    2.- Listar libros registrados
                    3.- Listar autores registrados
                    4.- Listar autores vivos en un determinado año
                    5.- Listar libros por idioma
                    0.- Salir
                    """;
            System.out.println(menu);
            opcion = sc.nextInt();
            sc.nextLine(); // Consumir el carácter de nueva línea

            switch (opcion) {
                case 1:
                    buscarSerieWeb();
                    break;

                case 2:
                    listarLibros();
                    break;
                case 3:
                    listarPorAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivos();
                    break;
                case 5:
                    listarPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la sesión");
                    break;
                default:
                    System.out.println("Opción invalida elija una opción valida :)");
            }
        }
    }




    public void buscarSerieWeb() {
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        String libroSolicitado = sc.nextLine();
        String json = consumoApi.obtenerDatos(URL_BASE + "?search=" + libroSolicitado.replace(" ", "+"));
        Datos datos = conversor.obtenerDatos(json, Datos.class);

        Optional<Libro> libroEncontrado = datos.resultados().stream()
                .map(Libro::new)
                .findFirst();

        if (libroEncontrado.isPresent()) {
            Autor autor = repositoryAutor.findByNombreContainsIgnoreCase(libroEncontrado.get().getAutor().getNombre());
            if (autor == null) {
                Autor nuevoAutor = libroEncontrado.get().getAutor();
                autor = repositoryAutor.save(nuevoAutor);
            }
            Libro libro = libroEncontrado.get();
            try {
                libro.setAutor(autor);
                repositorioLibro.save(libro);
                System.out.println(libro);
            } catch (DataIntegrityViolationException ex) {
                // Manejar la excepción de restricción única
                System.out.println("El libro ya existe en la base de datos.");
            }
        } else {
            System.out.println("No se encontró el libro buscado.");

        }
    }

    public void listarLibros() {
        List<Libro> libros = repositorioLibro.findAll();
        libros.forEach(System.out::println);
    }

    private void listarPorAutoresRegistrados() {
            List<Autor> autores = repositoryAutor.findAll();
        System.out.println("Resultados de busqueda: \n");
            autores.forEach(System.out::println);

    }

    private void listarAutoresVivos() {
    }

    private void listarPorIdioma() {
        idiomas();
        System.out.println("Escriba el idioma que desea buscar (ejemplo = es ) : ");
        String idiomaBuscado = sc.nextLine();
        List<Libro> librosEnIdioma = repositorioLibro.listarPorIdioma(idiomaBuscado);
            System.out.println();

        System.out.println("Resultados de busqueda: ");
        librosEnIdioma.forEach(System.out::println);
    }

    private void idiomas() {
        String idiomas = """
                es -> Español
                en -> Ingles
                fr -> Frances
                """;
        System.out.println(idiomas);
    }

}//cierre clase


/**
 * String json = consumoApi.obtenerDatos(URL_BASE);
 * System.out.println(json);
 * var datos = conversor.obtenerDatos(json, Datos.class);
 * System.out.println(datos);
 * <p>
 * //top 10 libros mas descargados
 * System.out.println("top 10 libros mas descargados");
 * datos.resultados().stream()
 * .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
 * .limit(10)
 * .map(libro -> libro.titulo().toUpperCase())
 * .forEach(System.out::println);
 * <p>
 * //busqueda de libros por nombre
 * <p>
 * System.out.print("Ingrese el nombre del libro que desea buscar: ");
 * String tituloLibro = sc.nextLine();
 * json = consumoApi.obtenerDatos(URL_BASE + "?search=" + tituloLibro.replace(" ", "+"));
 * var datosBusqueda = conversor.obtenerDatos(json, Datos.class);
 * Optional<DatosLibros> librosBuscado = datosBusqueda.resultados().stream()
 * .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
 * .findFirst();
 * if (librosBuscado.isPresent()) {
 * System.out.println("libro encontrado");
 * System.out.println(librosBuscado.get());
 * } else {
 * System.out.println("libro no encontrado");
 * }
 * <p>
 * <p>
 * //trabajando con estadisticas
 * DoubleSummaryStatistics est = datos.resultados().stream()
 * .filter(d -> d.numeroDeDescargas() > 0)
 * .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
 * System.out.println("Cantidad media de descargas: " + est.getAverage());
 * System.out.println("Cantidad maxiama de descargas: " + est.getMax());
 * System.out.println("Cantidad mimina de descargas: " + est.getMin());
 * System.out.println("Cantidad de registrso evaluados para calcular las estadisticas: " + est.getCount());
 */
