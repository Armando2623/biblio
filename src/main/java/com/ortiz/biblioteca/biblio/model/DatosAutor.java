package com.ortiz.biblioteca.biblio.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosAutor(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year")String fechaNacimiento,
        @JsonAlias("death_year")String fechaDefuncion,
        @JsonAlias("results") List<DatosLibros> librosList
//        @JsonAlias("obras")String fechaDefuncion
) {
}
