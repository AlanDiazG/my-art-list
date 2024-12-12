package edu.unam.springsecurity.service.genero;

import edu.unam.springsecurity.entities.Genero;
import edu.unam.springsecurity.repository.GeneroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    private GeneroRepository generoRepository;

    @Override
    public Genero obtenerGeneroPorDefecto() {
        return generoRepository.findById("1").orElse(null);
    }

    @Override
    public Genero obtenerGeneroPorId(Integer id) {
        return generoRepository.findById(String.valueOf(id)).orElse(null);
    }

    @Override
    public List<Genero> obtenerTodosLosGeneros() {
        return generoRepository.findAll();
    }
}