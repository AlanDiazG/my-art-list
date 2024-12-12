package edu.unam.springsecurity.repository;

import edu.unam.springsecurity.entities.Artista;
import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface VentaRepository extends JpaRepository<Venta, Integer> {
    Optional<Venta> findByUsuarioAndFinalizadaFalse(Usuario usuario);
    List<Venta> findByUsuarioAndFinalizadaTrue(Usuario usuario);
    Venta findByIdAndUsuario(Integer id, Usuario usuario);
}