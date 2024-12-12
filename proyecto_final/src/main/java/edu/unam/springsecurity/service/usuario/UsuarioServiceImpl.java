package edu.unam.springsecurity.service.usuario;


import edu.unam.springsecurity.auth.model.UserInfo;
import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService{
    @Autowired
    UsuarioRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Page<Usuario> buscarAllUsuario(Pageable pageable) {
        return usuarioRepository.findAll(pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> buscarAllUsuario() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public void guardar(Usuario barcoEntity) {
        usuarioRepository.save(barcoEntity);
    }

    @Override
    @Transactional
    public void borrar(Integer id) {
        usuarioRepository.deleteById(String.valueOf(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarUsuarioId(Integer id) {
        Optional<Usuario> op=usuarioRepository.findById(String.valueOf(id));
        return op.orElse(null);
    }

    @Override
    public List<Usuario> buscarUsuarioPatron(String patron) {
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorCorreo(String correo) {
        Optional<Usuario> op = usuarioRepository.findByCorreo(correo);
        return op.orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorSecUser(UserInfo secUser) {
        return usuarioRepository.findBySecUser(secUser);
    }
}
