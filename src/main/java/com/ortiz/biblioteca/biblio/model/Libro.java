package com.ortiz.biblioteca.biblio.model;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "Obras")
public class Libro {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String titulo;
    private List<String> idioma;
    private Double descargas;
    @OneToMany(mappedBy = "libro", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Autor> autor;

    public Libro() {
    }
    public Libro(DatosLibros datosLibros) {
        this.titulo = datosLibros.titulo();
        this.idioma = datosLibros.idiomas();
        this.descargas = datosLibros.numeroDescargas();

    }

    public List<Autor> getAutor() {
        return autor;
    }

    public void setAutor(List<Autor> autor) {
        autor.forEach(a->a.setLibro(this));
        this.autor = autor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Double getDescargas() {
        return descargas;
    }

    public void setDescargas(Double descargas) {
        this.descargas = descargas;
    }



    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", idioma=" + idioma +
                ", descargas=" + descargas +
                ", Autores=" + getAutor() +
                '}'+'\'' + '}';
    }
}