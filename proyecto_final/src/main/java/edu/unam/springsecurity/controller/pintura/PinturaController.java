package edu.unam.springsecurity.controller.pintura;

import edu.unam.springsecurity.entities.Pintura;
import edu.unam.springsecurity.service.pintura.PinturaService;
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
@RequestMapping("pintura")
public class PinturaController {

    @Autowired
    PinturaService pinturaService;

    @GetMapping("alta-pintura")
    public String altaCaladero(Model model) {
        log.info("Accediendo a la página de alta de pintura");
        Pintura pintura = new Pintura();
        model.addAttribute("contenido", "Alta de Pintura");
        model.addAttribute("pintura", pintura);

        return "pintura/alta-pintura";
    }

    @PostMapping("salvar-pintura")
    public String salvarPintura(@Valid @ModelAttribute("pintura") Pintura pintura,
                                BindingResult result, Model model,
                                RedirectAttributes flash) {
        log.info("Intentando guardar pintura: {}", pintura);
        if (result.hasErrors()) {
            log.warn("Errores de validación al guardar la pintura: {}", result.getAllErrors());
            model.addAttribute("contenido", "Error en los datos, verifique los campos");
            return "pintura/alta-pintura";
        }
        pinturaService.guardar(pintura);
        log.info("Pintura guardada exitosamente con ID: {}", pintura.getId());
        flash.addFlashAttribute("success", "Se almacenó la pintura con éxito");
        return "redirect:/pintura/lista-pintura";
    }

    @GetMapping("lista-pintura")
    public String listaPintura(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        log.info("Listando pinturas, página {}", page);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Pintura> pinturaEntities = pinturaService.buscarAllPintura(pageable);
        RenderPagina<Pintura> renderPagina = new RenderPagina<>("lista-pintura", pinturaEntities);
        model.addAttribute("pintura", pinturaEntities);
        model.addAttribute("page", renderPagina);
        model.addAttribute("contenido", "Lista de Pinturas");
        return "pintura/lista-pintura";
    }

    @GetMapping("eliminar-pintura/{id}")
    public String eliminarPintura(@PathVariable("id") Integer id, RedirectAttributes flash) {
        log.info("Eliminando pintura con ID: {}", id);
        pinturaService.borrar(id);
        log.info("Pintura con ID {} eliminada", id);
        flash.addFlashAttribute("success", "Se borró con éxito la pintura");
        return "redirect:/pintura/lista-pintura";
    }

    @GetMapping("modificar-pintura/{id}")
    public String saltoModificar(@PathVariable("id") Integer id, Model model) {
        log.info("Accediendo a modificación de pintura con ID: {}", id);
        Pintura pintura = pinturaService.buscarPinturaId(id);
        model.addAttribute("pintura", pintura);
        model.addAttribute("contenido", "Modificar Pintura");
        return "pintura/alta-pintura";
    }
}
