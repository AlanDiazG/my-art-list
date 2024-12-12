package edu.unam.springsecurity.repository;


import edu.unam.springsecurity.auth.model.UserInfo;
import edu.unam.springsecurity.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface UsuarioRepository extends JpaRepository<Usuario,String> {
    boolean existsByCorreo(String email);
    Optional<Usuario> findByCorreo(String correo);
    Usuario findBySecUser(UserInfo secUser);
}
