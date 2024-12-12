package edu.unam.springsecurity.controller.usuario;

import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import edu.unam.springsecurity.util.RenderPagina;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Slf4j
@Controller
@RequestMapping("usuario")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;

    @GetMapping("alta-usuario")
    public String altaCaladero(Model model) {
        log.info("Accediendo a la página de alta de usuario");
        Usuario usuario = new Usuario();
        model.addAttribute("contenido", "Alta de Usuario");
        model.addAttribute("usuario", usuario);

        return "usuario/alta-usuario";
    }

    @PostMapping("salvar-usuario")
    public String salvarUsuario(@Valid @ModelAttribute("usuario") Usuario usuario,
                                BindingResult result, Model model,
                                RedirectAttributes flash) {
        log.info("Intentando guardar usuario: {}", usuario);
        if (result.hasErrors()) {
            log.warn("Errores de validación al guardar el usuario: {}", result.getAllErrors());
            model.addAttribute("contenido", "Error en los datos, verifique los campos");
            return "usuario/alta-usuario";
        }
        usuarioService.guardar(usuario);
        log.info("Usuario guardado exitosamente con ID: {}", usuario.getId());
        flash.addFlashAttribute("success", "Se almacenó el usuario con éxito");
        return "redirect:/usuario/lista-usuario";
    }

    @GetMapping("lista-usuario")
    public String listaUsuario(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        log.info("Listando usuarios, página {}", page);
        Pageable pageable = PageRequest.of(page, 4);
        Page<Usuario> usuarioEntities = usuarioService.buscarAllUsuario(pageable);
        RenderPagina<Usuario> renderPagina = new RenderPagina<>("lista-usuario", usuarioEntities);
        model.addAttribute("usuario", usuarioEntities);
        model.addAttribute("page", renderPagina);
        model.addAttribute("contenido", "Lista de Usuarios");
        return "usuario/lista-usuario";
    }

    @GetMapping("eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable("id") Integer id, RedirectAttributes flash) {
        log.info("Eliminando usuario con ID: {}", id);
        usuarioService.borrar(id);
        log.info("Usuario con ID {} eliminado", id);
        flash.addFlashAttribute("success", "Se borró con éxito el usuario");
        return "redirect:/usuario/lista-usuario";
    }

    @GetMapping("modificar-usuario/{id}")
    public String saltoModificar(@PathVariable("id") Integer id, Model model) {
        log.info("Accediendo a modificación de usuario con ID: {}", id);
        Usuario usuario = usuarioService.buscarUsuarioId(id);
        model.addAttribute("usuario", usuario);
        model.addAttribute("contenido", "Modificar Usuario");
        return "usuario/alta-usuario";
    }
}
