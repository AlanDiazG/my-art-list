package edu.unam.springsecurity.service.producto;


import edu.unam.springsecurity.entities.Producto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductoService {
    Page<Producto> buscarAllProducto(Pageable pageable);
    List<Producto> buscarAllProducto();
    void guardar(Producto producto);
    void borrar(String id);
    Producto buscarProductoId(String id);
    List<Producto> buscarProductoPatron(String patron);

}
