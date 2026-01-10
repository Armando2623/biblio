package com.ortiz.biblioteca.biblio.repository;

import com.ortiz.biblioteca.biblio.model.Autor;
import com.ortiz.biblioteca.biblio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IOAutor extends JpaRepository<Autor, Long> {
    @Query("select a from Autor a where a.fechaNacimiento = :anio")
    List<Autor> findAño(@Param("anio") String anio);
}
