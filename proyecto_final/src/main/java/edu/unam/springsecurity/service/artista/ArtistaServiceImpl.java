package edu.unam.springsecurity.service.artista;


import edu.unam.springsecurity.entities.Artista;
import edu.unam.springsecurity.repository.ArtistaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaServiceImpl implements ArtistaService {
    @Autowired
    ArtistaRepository artistaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Artista> buscarAllArtista(Pageable pageable) {
        return artistaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Artista> buscarAllArtista() {
        return artistaRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Artista barcoEntity) {
        artistaRepository.save(barcoEntity);
    }

    @Override
    @Transactional
    public void borrar(Integer id) {
        artistaRepository.deleteById(String.valueOf(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Artista buscarArtistaId(Integer id) {
        Optional<Artista> op=artistaRepository.findById(String.valueOf(id));
        return op.orElse(null);
    }

    @Override
    public List<Artista> buscarArtistaPatron(String patron) {
        return null;
    }
}
