package edu.unam.springsecurity.repository;

import edu.unam.springsecurity.entities.Artista;
import edu.unam.springsecurity.entities.CarritoProducto;
import edu.unam.springsecurity.entities.Producto;
import edu.unam.springsecurity.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CarritoProductoRepository extends JpaRepository<CarritoProducto, Integer> {
    Optional<CarritoProducto> findByVentaAndProducto(Venta venta, Producto producto);
    List<CarritoProducto> findByVenta(Venta venta);
    void deleteByVenta(Venta venta);
}