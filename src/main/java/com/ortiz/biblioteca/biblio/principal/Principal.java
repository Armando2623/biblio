package com.ortiz.biblioteca.biblio.principal;



import com.ortiz.biblioteca.biblio.model.*;
import com.ortiz.biblioteca.biblio.repository.IOAutor;
import com.ortiz.biblioteca.biblio.repository.IOLibro;
import com.ortiz.biblioteca.biblio.service.APIconsumo;
import com.ortiz.biblioteca.biblio.service.ConvierteDatos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class Principal {
    private static final String Url_Base = "https://gutendex.com/books/";
    private APIconsumo consumoAPI = new APIconsumo();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private Scanner teclado = new Scanner(System.in);
    private IOLibro repoLibro;
    private IOAutor repoAutor;
    private List<Libro> libros;
    private List<Autor> autores;

    @Autowired
    public Principal(IOLibro ioLibro, IOAutor ioAutor){
    this.repoLibro = ioLibro;
    this.repoAutor = ioAutor;
    }


    public void muestraMenu() {
        var json = consumoAPI.obtenerDatos(Url_Base);
        System.out.println(json);
        var datos = convierteDatos.obtenerDatos(json, Datos.class);
        System.out.println(datos);

        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    1 - Buscar libro por titulo
                    2 - Buscar libros registrados
                    3 - Listar autores de libros
                    4 - Listar autores vivos en un determinado año
                    0 - salir
                    """;
            System.out.println(menu);
            opcion = teclado.nextInt();
            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    librosRegistrados();
                    break;
                case 3:
                    AutoresLibros();
                    break;
                case 4:
                    autoresAño();
                    break;

            }
        }


    }

    private void buscarLibro() {
        DatosLibros datos = getDatos();
        Libro libro = new Libro(datos);
            List<Autor> autores = datos.autor().stream()
                            .map(Autor::new)
                                    .toList();
            libro.setAutor(autores);

        repoLibro.save(libro);
        System.out.println(datos);



    }

    private void librosRegistrados() {
        libros = repoLibro.findAll();

        libros.stream()
                        .forEach(System.out::println);

    }

    private void AutoresLibros() {
       libros = repoLibro.findAllConAutores();
       autores = libros.stream()
               .filter(l->l.getAutor()!= null)
               .flatMap(l->l.getAutor().stream())
               .toList();
        autores.forEach(a-> System.out.println(a.getNombre()
        + " - " + a.getLibro().getTitulo()));

    }

    private void autoresAño() {
        System.out.println("Ingrese el año del autor");
        var anio = teclado.nextLine();

autores = repoAutor.findAño(anio);
autores.forEach(a-> System.out.println(a.getNombre()));
    }

    private void librosIdioma() {

    }


    private DatosLibros getDatos() {
        System.out.println("Ingrese el nombre para buscar el libro");
        var tituloLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(Url_Base + "?search=" + tituloLibro.replace(" ", "+"));
        System.out.println(json);
        var datosbusqueda= convierteDatos.obtenerDatos(json, Datos.class);

        Optional<DatosLibros> librosBuscados = datosbusqueda.librosList().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (librosBuscados.isPresent()) {
            System.out.println("Libro encontrado");
           // System.out.println(librosBuscados.get());
            return librosBuscados.get();

        } else {
            System.out.println("Libro no encontrado");
            return null;
        }

    }
}

