package edu.unam.springsecurity.repository;

import edu.unam.springsecurity.entities.Tarjeta;
import edu.unam.springsecurity.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarjetaRepository extends JpaRepository<Tarjeta, Integer> {

    // Obtener todas las tarjetas de un usuario
    List<Tarjeta> findByUsuario(Usuario usuario);

    Tarjeta findByIdAndUsuario(Integer id, Usuario usuario);
}
