package com.ortiz.biblioteca.biblio.service;

public interface IConviertedatos {
    <T> T obtenerDatos(String json, Class <T> clase);

}
