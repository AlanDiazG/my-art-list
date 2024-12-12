package edu.unam.springsecurity.service.genero;

import edu.unam.springsecurity.entities.Genero;

import java.util.List;

public interface GeneroService {
    Genero obtenerGeneroPorDefecto();
    Genero obtenerGeneroPorId(Integer id);
    List<Genero> obtenerTodosLosGeneros();
}
