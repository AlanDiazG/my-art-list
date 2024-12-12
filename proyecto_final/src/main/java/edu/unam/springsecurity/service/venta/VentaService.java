package edu.unam.springsecurity.service.venta;

import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;

import java.util.List;

public interface VentaService {
    Venta guardarVenta(Venta venta);
    List<Venta> obtenerVentasFinalizadasPorUsuario(Usuario usuario);
    Venta obtenerVentaPorIdYUsuario(Integer id, Usuario usuario);
}
