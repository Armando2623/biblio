package com.ortiz.biblioteca.biblio.repository;

import com.ortiz.biblioteca.biblio.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IOLibro  extends JpaRepository<Libro, Long> {

    @Query("SELECT DISTINCT l FROM Libro l JOIN FETCH l.autor")
    List<Libro> findAllConAutores();


}
