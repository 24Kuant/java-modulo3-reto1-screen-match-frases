package com.aluracursos.screematch.administrafrasescelebres.service;

public interface IConvierteDatos {
    <T> T obtenerDatos(String json, Class<T> clase);
}
