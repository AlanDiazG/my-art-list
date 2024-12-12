package edu.unam.springsecurity.controller.usuario;


import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import edu.unam.springsecurity.util.RenderPagina;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    UsuarioService usuarioService;

    @GetMapping("alta-usuario")
    public String altaCaladero(Model model) {
        Usuario usuario = new Usuario();
        model.addAttribute("contenido", "Alta de Usuario");
        model.addAttribute("usuario", usuario);

        return "usuario/alta-usuario";
    }

    @PostMapping("salvar-usuario")
    public String salvarUsuario(@Valid @ModelAttribute("usuario")Usuario usuario
            , BindingResult result, Model model,
                              RedirectAttributes flash){
        System.out.println(usuario);
        if(result.hasErrors()){
            model.addAttribute("contenido","Error en el nombre, no debe ser vacío");
            return "usuario/alta-usuario";
        }
        usuarioService.guardar(usuario);
        //model.addAttribute("success","Se almaceno lote con éxito");
        //model.addAttribute("lote",lote);
        flash.addFlashAttribute("success","Se almaceno Usuario con éxito");
//        return "lote/alta-lote";
        return "redirect:/usuario/lista-usuario";
    }

    @GetMapping("lista-usuario")
    public String listaUsuario(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page,4);
        Page<Usuario> usuarioEntities = usuarioService.buscarAllUsuario(pageable);
        RenderPagina<Usuario> renderPagina = new RenderPagina<>("lista-usuario", usuarioEntities);
        model.addAttribute("usuario", usuarioEntities);
        model.addAttribute("page",renderPagina);
        model.addAttribute("contenido","Lista de Usuarios");
        return "usuario/lista-usuario";
    }

    @GetMapping("eliminar-usuario/{id}")
    public String eliminarUsuario(@PathVariable("id")Integer id,RedirectAttributes flash){
        usuarioService.borrar(id);
        flash.addFlashAttribute("success","Se borro con exito Usuario");
        return "redirect:/usuario/lista-usuario";
    }

    @GetMapping("modificar-usuario/{id}")
    public String saltoModificar(@PathVariable("id")Integer id, Model model){
        Usuario usuario = usuarioService.buscarUsuarioId(id);
        model.addAttribute("usuario",usuario);
        model.addAttribute("contenido","Modificar Usuario");
        return "usuario/alta-usuario";
    }


}
