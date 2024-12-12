package edu.unam.springsecurity.controller.producto;

import edu.unam.springsecurity.entities.Producto;
import edu.unam.springsecurity.service.producto.ProductoService;
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
@RequestMapping("producto")
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("alta-producto")
    public String altaCaladero(Model model) {
        log.info("Accediendo a la página de alta de producto");
        Producto producto = new Producto();
        model.addAttribute("contenido", "Alta de Producto");
        model.addAttribute("producto", producto);

        return "producto/alta-producto";
    }

    @PostMapping("salvar-producto")
    public String salvarProducto(@Valid @ModelAttribute("producto") Producto producto,
                                 BindingResult result, Model model,
                                 RedirectAttributes flash) {
        log.info("Intentando guardar producto: {}", producto);
        if (result.hasErrors()) {
            log.warn("Errores de validación al guardar el producto: {}", result.getAllErrors());
            model.addAttribute("contenido", "Error en los datos, verifique los campos");
            return "producto/alta-producto";
        }
        productoService.guardar(producto);
        log.info("Producto guardado exitosamente con clave: {}", producto.getClave());
        flash.addFlashAttribute("success", "Se almacenó el producto con éxito");
        return "redirect:/producto/lista-producto";
    }

    @GetMapping("lista-producto")
    public String listaProducto(@RequestParam(name = "page", defaultValue = "0") int page, Model model) {
        log.info("Listando productos, página {}", page);
        Pageable pageable = PageRequest.of(page, 5);
        Page<Producto> productoEntities = productoService.buscarAllProducto(pageable);
        RenderPagina<Producto> renderPagina = new RenderPagina<>("lista-producto", productoEntities);
        model.addAttribute("producto", productoEntities);
        model.addAttribute("page", renderPagina);
        model.addAttribute("contenido", "Lista de Productos");
        return "producto/lista-producto";
    }

    @GetMapping("eliminar-producto/{id}")
    public String eliminarProducto(@PathVariable("id") String id, RedirectAttributes flash) {
        log.info("Eliminando producto con clave: {}", id);
        productoService.borrar(id);
        log.info("Producto con clave {} eliminado", id);
        flash.addFlashAttribute("success", "Se borró con éxito el producto");
        return "redirect:/producto/lista-producto";
    }

    @GetMapping("modificar-producto/{id}")
    public String saltoModificar(@PathVariable("id") String id, Model model) {
        log.info("Accediendo a modificación de producto con clave: {}", id);
        Producto producto = productoService.buscarProductoId(id);
        model.addAttribute("producto", producto);
        model.addAttribute("contenido", "Modificar Producto");
        return "producto/alta-producto";
    }
}
