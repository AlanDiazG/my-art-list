package edu.unam.springsecurity.service.producto;


import edu.unam.springsecurity.entities.Producto;
import edu.unam.springsecurity.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    ProductoRepository productoRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Producto> buscarAllProducto(Pageable pageable) {
        return productoRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> buscarAllProducto() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Producto barcoEntity) {
        productoRepository.save(barcoEntity);
    }

    @Override
    @Transactional
    public void borrar(String id) {
        productoRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Producto buscarProductoId(String id) {
        Optional<Producto> op=productoRepository.findById(id);
        return op.orElse(null);
    }

    @Override
    public List<Producto> buscarProductoPatron(String patron) {
        return null;
    }
}
