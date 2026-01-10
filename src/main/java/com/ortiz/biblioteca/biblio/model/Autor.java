package com.ortiz.biblioteca.biblio.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Autores")
public class Autor {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private long Id;
private String nombre;
private String fechaNacimiento;
private String fechaDefuncion;
@ManyToOne
@JoinColumn(name = "libro_id")
private Libro libro;

    public Autor() {
    }

    public Autor(DatosAutor datosAutor) {

        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = datosAutor.fechaNacimiento();
        this.fechaDefuncion = datosAutor.fechaDefuncion();

    }


    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getFechaDefuncion() {
        return fechaDefuncion;
    }

    public void setFechaDefuncion(String fechaDefuncion) {
        this.fechaDefuncion = fechaDefuncion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "Id=" + Id +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", fechaNacimiento='" + fechaDefuncion + '\'' +
                '}';
    }
}
