package org.gvasquez.desafio.principal;

import org.gvasquez.desafio.model.Datos;
import org.gvasquez.desafio.model.DatosLibros;
import org.gvasquez.desafio.service.ConsumoApi;
import org.gvasquez.desafio.service.Conviertedatos;
import org.gvasquez.desafio.service.IConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class Principal {

    private static final String URL_BASE = "https://gutendex.com/books/";

   private ConsumoApi consumoApi=new ConsumoApi();
   private Conviertedatos conversor= new Conviertedatos();
   private Scanner sc=new Scanner(System.in);


    public void muestraMenu() {
        String json = consumoApi.obtenerDatos(URL_BASE);
        System.out.println(json);
        var datos=conversor.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        //top 10 libros mas descargados
        System.out.println("top 10 libros mas descargados");
        datos.resultados().stream()
                .sorted(Comparator.comparing(DatosLibros::numeroDeDescargas).reversed())
                .limit(10)
                .map(libro -> libro.titulo().toUpperCase())
                .forEach(System.out::println);

        //busqueda de libros por nombre
        System.out.print("Ingrese el nombre del libro que desea buscar: ");
        String tituloLibro=sc.nextLine();
        json=consumoApi.obtenerDatos(URL_BASE+"?search="+tituloLibro.replace(" ","+"));
        var datosBusqueda=conversor.obtenerDatos(json,Datos.class);
        Optional<DatosLibros> librosBuscado = datosBusqueda.resultados().stream()
                .filter(l-> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if(librosBuscado.isPresent()){
            System.out.println("libro encontrado");
            System.out.println(librosBuscado.get());
        }else {
            System.out.println("libro no encontrado");
        }

        //trabajando con estadisticas
        DoubleSummaryStatistics est=datos.resultados().stream()
                .filter(d-> d.numeroDeDescargas()>0)
                .collect(Collectors.summarizingDouble(DatosLibros::numeroDeDescargas));
        System.out.println("Cantidad media de descargas: "+est.getAverage());
        System.out.println("Cantidad maxiama de descargas: "+est.getMax());
        System.out.println("Cantidad mimina de descargas: "+est.getMin());
        System.out.println("Cantidad de registrso evaluados para calcular las estadisticas: "+est.getCount());;
    }
}