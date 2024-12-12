package edu.unam.springsecurity.service.carrito;

import edu.unam.springsecurity.entities.CarritoProducto;
import edu.unam.springsecurity.entities.Producto;
import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import edu.unam.springsecurity.repository.CarritoProductoRepository;
import edu.unam.springsecurity.repository.ProductoRepository;
import edu.unam.springsecurity.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class CarritoServiceImpl implements CarritoService{

    @Autowired
    private VentaRepository ventaRepository;

    @Autowired
    private CarritoProductoRepository carritoProductoRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    @Transactional
    public Venta obtenerCarritoUsuario(Usuario usuario) {
        // Buscamos una venta (carrito) que no haya sido finalizada (por ejemplo, con total 0)
        Optional<Venta> optionalVenta = ventaRepository.findByUsuarioAndFinalizadaFalse(usuario);

        if (optionalVenta.isPresent()) {
            return optionalVenta.get();
        } else {
            // Si no existe, creamos una nueva venta (carrito)
            Venta carrito = new Venta();
            carrito.setUsuario(usuario);
            carrito.setTotal(0.0);
            carrito.setFecha(new Date());
            carrito.setFinalizada(false); // Necesitamos agregar este campo para indicar si la venta está finalizada o es un carrito en curso.
            ventaRepository.save(carrito);
            return carrito;
        }
    }

    @Override
    @Transactional
    public void agregarProducto(Venta carrito, Producto producto, int cantidad) {
        // Verificar si el producto ya está en el carrito
        Optional<CarritoProducto> optionalCarritoProducto = carritoProductoRepository.findByVentaAndProducto(carrito, producto);

        if (optionalCarritoProducto.isPresent()) {
            // Si ya está, actualizamos la cantidad
            CarritoProducto carritoProducto = optionalCarritoProducto.get();
            carritoProducto.setCantidad(carritoProducto.getCantidad() + cantidad);
            carritoProductoRepository.save(carritoProducto);
        } else {
            // Si no está, lo agregamos
            CarritoProducto carritoProducto = new CarritoProducto();
            carritoProducto.setVenta(carrito);
            carritoProducto.setProducto(producto);
            carritoProducto.setCantidad(cantidad);
            carritoProductoRepository.save(carritoProducto);
        }
        actualizarTotal(carrito);
    }

    @Override
    @Transactional
    public void eliminarProducto(Venta carrito, Producto producto) {
        Optional<CarritoProducto> optionalCarritoProducto = carritoProductoRepository.findByVentaAndProducto(carrito, producto);

        if (optionalCarritoProducto.isPresent()) {
            carritoProductoRepository.delete(optionalCarritoProducto.get());
            actualizarTotal(carrito);
        }
    }

    @Override
    @Transactional
    public void vaciarCarrito(Venta carrito) {
        carritoProductoRepository.deleteByVenta(carrito);
        carrito.setTotal(0.0);
        ventaRepository.save(carrito);
    }

    @Override
    @Transactional
    public void actualizarTotal(Venta carrito) {
        Double total = carritoProductoRepository.findByVenta(carrito).stream()
                .mapToDouble(cp -> cp.getProducto().getPrecio() * cp.getCantidad())
                .sum();
        carrito.setTotal(total);
        ventaRepository.save(carrito);
    }

    @Override
    public Venta crearNuevoCarritoParaUsuario(Usuario usuario) {
        Venta nuevoCarrito = new Venta();
        nuevoCarrito.setUsuario(usuario);
        nuevoCarrito.setTotal(0.0);
        nuevoCarrito.setFecha(new Date());
        nuevoCarrito.setFinalizada(false);
        return ventaRepository.save(nuevoCarrito);
    }

}
