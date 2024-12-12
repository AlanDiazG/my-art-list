package edu.unam.springsecurity.service.pintura;


import edu.unam.springsecurity.entities.Pintura;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PinturaService {
    Page<Pintura> buscarAllPintura(Pageable pageable);
    List<Pintura> buscarAllPintura();
    void guardar(Pintura pintura);
    void borrar(Integer id);
    Pintura buscarPinturaId(Integer id);
    List<Pintura> buscarPinturaPatron(String patron);

}
