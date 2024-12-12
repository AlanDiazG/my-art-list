package edu.unam.springsecurity.service.carrito;

import edu.unam.springsecurity.entities.Producto;
import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public interface CarritoService {
    Venta obtenerCarritoUsuario(Usuario usuario);
    void agregarProducto(Venta carrito, Producto producto, int cantidad);
    void eliminarProducto(Venta carrito, Producto producto);
    void vaciarCarrito(Venta carrito);
    void actualizarTotal(Venta carrito);
    Venta crearNuevoCarritoParaUsuario(Usuario usuario);
}
