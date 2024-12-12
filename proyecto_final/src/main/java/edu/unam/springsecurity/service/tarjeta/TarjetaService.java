package edu.unam.springsecurity.service.tarjeta;

import edu.unam.springsecurity.entities.Tarjeta;
import edu.unam.springsecurity.entities.Usuario;

import java.util.List;

public interface TarjetaService {
    List<Tarjeta> obtenerTarjetasPorUsuario(Usuario usuario);
    Tarjeta obtenerTarjetaPorIdYUsuario(Integer id, Usuario usuario);
    Tarjeta guardarTarjeta(Tarjeta tarjeta);
    void actualizarSaldo(Tarjeta tarjeta, Float nuevoSaldo);
}
