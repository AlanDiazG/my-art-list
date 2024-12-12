package edu.unam.springsecurity.controller.pintura;

import edu.unam.springsecurity.entities.Pintura;
import edu.unam.springsecurity.service.pintura.PinturaService;
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
@RequestMapping("pintura")
public class PinturaController {

    @Autowired
    PinturaService pinturaService;

    @GetMapping("alta-pintura")
    public String altaCaladero(Model model) {
        Pintura pintura = new Pintura();
        model.addAttribute("contenido", "Alta de Pintura");
        model.addAttribute("pintura", pintura);

        return "pintura/alta-pintura";
    }

    @PostMapping("salvar-pintura")
    public String salvarPintura(@Valid @ModelAttribute("pintura")Pintura pintura
            , BindingResult result, Model model,
                                 RedirectAttributes flash){
        System.out.println(pintura);
        if(result.hasErrors()){
            model.addAttribute("contenido","Error en el nombre, no debe ser vacío");
            return "pintura/alta-pintura";
        }
        pinturaService.guardar(pintura);
        //model.addAttribute("success","Se almaceno lote con éxito");
        //model.addAttribute("lote",lote);
        flash.addFlashAttribute("success","Se almaceno Pintura con éxito");
//        return "lote/alta-lote";
        return "redirect:/pintura/lista-pintura";
    }

    @GetMapping("lista-pintura")
    public String listaPintura(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page,5);
        Page<Pintura> pinturaEntities = pinturaService.buscarAllPintura(pageable);
        RenderPagina<Pintura> renderPagina = new RenderPagina<>("lista-pintura", pinturaEntities);
        model.addAttribute("pintura", pinturaEntities);
        model.addAttribute("page",renderPagina);
        model.addAttribute("contenido","Lista de Pinturas");
        return "pintura/lista-pintura";
    }

    @GetMapping("eliminar-pintura/{id}")
    public String eliminarPintura(@PathVariable("id")Integer id,RedirectAttributes flash){
        pinturaService.borrar(id);
        flash.addFlashAttribute("success","Se borro con exito Pintura");
        return "redirect:/pintura/lista-pintura";
    }

    @GetMapping("modificar-pintura/{id}")
    public String saltoModificar(@PathVariable("id")Integer id, Model model){
        Pintura pintura = pinturaService.buscarPinturaId(id);
        model.addAttribute("pintura",pintura);
        model.addAttribute("contenido","Modificar Pintura");
        return "pintura/alta-pintura";
    }
    
}
