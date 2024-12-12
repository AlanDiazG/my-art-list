package edu.unam.springsecurity.service.carreraProfesional;

import edu.unam.springsecurity.entities.CarreraProfesional;
import edu.unam.springsecurity.repository.CarreraProfesionalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarreraProfesionalServiceImpl implements CarreraProfesionalService {

    @Autowired
    private CarreraProfesionalRepository carreraProfesionalRepository;

    @Override
    public CarreraProfesional obtenerCarreraPorDefecto() {
        // Supongamos que la carrera con id = 1 es la por defecto.
        return carreraProfesionalRepository.findById("1").orElse(null);
    }

    @Override
    public CarreraProfesional obtenerCarreraPorId(Integer id) {
        return carreraProfesionalRepository.findById(String.valueOf(id)).orElse(null);
    }

    @Override
    public List<CarreraProfesional> obtenerTodasLasCarreras() {
        return carreraProfesionalRepository.findAll();
    }
}