package edu.unam.springsecurity.service.pintura;


import edu.unam.springsecurity.entities.Pintura;
import edu.unam.springsecurity.repository.PinturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PinturaServiceImpl implements PinturaService {
    @Autowired
    PinturaRepository pinturaRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Pintura> buscarAllPintura(Pageable pageable) {
        return pinturaRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Pintura> buscarAllPintura() {
        return pinturaRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Pintura barcoEntity) {
        pinturaRepository.save(barcoEntity);
    }

    @Override
    @Transactional
    public void borrar(Integer id) {
        pinturaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Pintura buscarPinturaId(Integer id) {
        Optional<Pintura> op=pinturaRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public List<Pintura> buscarPinturaPatron(String patron) {
        return null;
    }
}
