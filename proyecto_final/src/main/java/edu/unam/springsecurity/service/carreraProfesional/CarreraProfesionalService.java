package edu.unam.springsecurity.service.carreraProfesional;

import edu.unam.springsecurity.entities.CarreraProfesional;

import java.util.List;

public interface CarreraProfesionalService {
    CarreraProfesional obtenerCarreraPorDefecto();
    CarreraProfesional obtenerCarreraPorId(Integer id);
    List<CarreraProfesional> obtenerTodasLasCarreras();
}
