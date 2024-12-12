package edu.unam.springsecurity.controller.producto;


import edu.unam.springsecurity.entities.Producto;
import edu.unam.springsecurity.service.producto.ProductoService;
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
@RequestMapping("producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("alta-producto")
    public String altaCaladero(Model model) {
        Producto producto = new Producto();
        model.addAttribute("contenido", "Alta de Producto");
        model.addAttribute("producto", producto);

        return "producto/alta-producto";
    }

    @PostMapping("salvar-producto")
    public String salvarProducto(@Valid @ModelAttribute("producto")Producto producto
            , BindingResult result, Model model,
                                RedirectAttributes flash){
        System.out.println(producto);
        if(result.hasErrors()){
            model.addAttribute("contenido","Error en el nombre, no debe ser vacío");
            return "producto/alta-producto";
        }
        productoService.guardar(producto);
        //model.addAttribute("success","Se almaceno lote con éxito");
        //model.addAttribute("lote",lote);
        flash.addFlashAttribute("success","Se almaceno Producto con éxito");
//        return "lote/alta-lote";
        return "redirect:/producto/lista-producto";
    }

    @GetMapping("lista-producto")
    public String listaProducto(@RequestParam(name = "page", defaultValue = "0")int page, Model model){
        Pageable pageable = PageRequest.of(page,5);
        Page<Producto> productoEntities = productoService.buscarAllProducto(pageable);
        RenderPagina<Producto> renderPagina = new RenderPagina<>("lista-producto", productoEntities);
        model.addAttribute("producto", productoEntities);
        model.addAttribute("page",renderPagina);
        model.addAttribute("contenido","Lista de Productos");
        return "producto/lista-producto";
    }

    @GetMapping("eliminar-producto/{id}")
    public String eliminarProducto(@PathVariable("id")String id,RedirectAttributes flash){
        productoService.borrar(id);
        flash.addFlashAttribute("success","Se borro con exito Producto");
        return "redirect:/producto/lista-producto";
    }

    @GetMapping("modificar-producto/{id}")
    public String saltoModificar(@PathVariable("id")String id, Model model){
        Producto producto = productoService.buscarProductoId(id);
        model.addAttribute("producto",producto);
        model.addAttribute("contenido","Modificar Producto");
        return "producto/alta-producto";
    }
}
