package edu.unam.springsecurity.service.artista;


import edu.unam.springsecurity.entities.Artista;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ArtistaService {
    Page<Artista> buscarAllArtista(Pageable pageable);
    List<Artista> buscarAllArtista();
    void guardar(Artista artista);
    void borrar(Integer id);
    Artista buscarArtistaId(Integer id);
    List<Artista> buscarArtistaPatron(String patron);

}
