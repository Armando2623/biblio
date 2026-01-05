package com.ortiz.biblioteca.biblio.principal;



import com.ortiz.biblioteca.biblio.model.Datos;
import com.ortiz.biblioteca.biblio.model.DatosLibros;
import com.ortiz.biblioteca.biblio.service.APIconsumo;
import com.ortiz.biblioteca.biblio.service.ConvierteDatos;

import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal{
private static final String Url_Base="https://gutendex.com/books/";
private APIconsumo consumoAPI = new APIconsumo();
private ConvierteDatos convierteDatos = new ConvierteDatos();
private Scanner teclado =new Scanner(System.in);

public void muestraMenu(){
    var json = consumoAPI.obtenerDatos(Url_Base);
    System.out.println(json);
    var datos = convierteDatos.obtenerDatos(json, Datos.class);
    System.out.println(datos);



    //top 10 libros mas descargados
    System.out.println("Top 10 libros mas descargados");

    datos.librosList().stream()
            .sorted(Comparator.comparing(DatosLibros::numeroDescargas).reversed())
            .limit(10)
            .map(l->l.titulo().toUpperCase())
            .forEach(System.out::println);


    //busqueda por nombre
    System.out.println("Ingrese el nombre para buscar el libro");
    var tituloLibro = teclado.nextLine();
    json = consumoAPI.obtenerDatos(Url_Base+"?search=" + tituloLibro.replace(" ", "+"));
    var datosbusqueda = convierteDatos.obtenerDatos(json, Datos.class);
    Optional<DatosLibros> librosBuscados = datosbusqueda.librosList().stream()
            .filter(l->l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
            .findFirst();
    if (librosBuscados.isPresent()){
        System.out.println("Libro encontrado");
        System.out.println(librosBuscados.get());

    }else {
        System.out.println("Libro no encontrado");
    }


    //trabajanod con estadisticas
    DoubleSummaryStatistics est = datosbusqueda.librosList().stream()
            .filter(d->d.numeroDescargas()>8)
            .collect(Collectors.summarizingDouble(DatosLibros::numeroDescargas));
    System.out.println("Cantidad de descargas" + est.getAverage());
    System.out.println("Cantidad maxima de descargas" + est.getMax());
    System.out.println("Cantidad minima de descargas" + est.getMin());
    System.out.println("Cantidad de registros evaluados" + est.getCount());
}


}