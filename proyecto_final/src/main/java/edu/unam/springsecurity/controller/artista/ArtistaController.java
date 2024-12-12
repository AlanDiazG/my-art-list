package edu.unam.springsecurity.controller.artista;


import edu.unam.springsecurity.entities.Artista;
import edu.unam.springsecurity.service.artista.ArtistaService;
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
@RequestMapping("artista")
public class ArtistaController {
    @Autowired
    ArtistaService artistaService;

    @GetMapping("alta-artista")
    public String altaCaladero(Model model) {
        log.info("Accediendo a la página de alta de artista");
        Artista artista = new Artista();
        model.addAttribute("contenido", "Alta de Artista");
        model.addAttribute("artista", artista);

        return "artista/alta-artista";
    }

    @PostMapping("salvar-artista")
    public String salvarArtista(@Valid @ModelAttribute("artista")Artista artista
            , BindingResult result, Model model,
                              RedirectAttributes flash){
        log.info("Intentando guardar artista: {}", artista);
        if(result.hasErrors()){
            log.warn("Errores de validación al guardar el artista: {}", result.getAllErrors());
            model.addAttribute("contenido","Error en el nombre, no debe ser vacío");
            return "artista/alta-artista";
        }
        artistaService.guardar(artista);
        log.info("Artista guardado exitosamente con ID: {}", artista.getId());
        //model.addAttribute("success","Se almaceno lote con éxito");
        //model.addAttribute("lote",lote);
        flash.addFlashAttribute("success","Se almaceno Artista con éxito");
//        return "lote/alta-lote";
        return "redirect:/artista/lista-artista";
    }

    @GetMapping("lista-artista")
    public String listaArtista(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page,10);
        Page<Artista> artistaEntities = artistaService.buscarAllArtista(pageable);
        RenderPagina<Artista> renderPagina = new RenderPagina<>("lista-artista", artistaEntities);
        model.addAttribute("artista", artistaEntities);
        model.addAttribute("page",renderPagina);
        model.addAttribute("contenido","Lista de Artistas");
        return "artista/lista-artista";
    }

    @GetMapping("eliminar-artista/{id}")
    public String eliminarArtista(@PathVariable("id")Integer id,RedirectAttributes flash){
        artistaService.borrar(id);
        flash.addFlashAttribute("success","Se borro con exito Artista");
        return "redirect:/artista/lista-artista";
    }

    @GetMapping("modificar-artista/{id}")
    public String saltoModificar(@PathVariable("id")Integer id, Model model){
        Artista artista = artistaService.buscarArtistaId(id);
        model.addAttribute("artista",artista);
        model.addAttribute("contenido","Modificar Artista");
        return "artista/alta-artista";
    }


}
