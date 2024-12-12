package edu.unam.springsecurity.service.usuario;


import edu.unam.springsecurity.auth.model.UserInfo;
import edu.unam.springsecurity.entities.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UsuarioService {
    Page<Usuario> buscarAllUsuario(Pageable pageable);
    List<Usuario> buscarAllUsuario();
    void guardar(Usuario usuario);
    void borrar(Integer id);
    Usuario buscarUsuarioId(Integer id);
    List<Usuario> buscarUsuarioPatron(String patron);
    Usuario buscarPorCorreo(String correo);
    Usuario buscarPorSecUser(UserInfo secUser);
}
