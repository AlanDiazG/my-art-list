package edu.unam.springsecurity.controller.carrito;

import edu.unam.springsecurity.auth.model.UserInfo;
import edu.unam.springsecurity.entities.CarritoProducto;
import edu.unam.springsecurity.entities.Producto;
import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import edu.unam.springsecurity.security.model.UserDetailsImpl;
import edu.unam.springsecurity.service.carrito.CarritoService;
import edu.unam.springsecurity.service.producto.ProductoService;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

@Controller
public class CarritoController {

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private ProductoService productoService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/agregarAlCarrito/{productoId}")
    public String agregarAlCarrito(@PathVariable("productoId") String productoId,
                                   @RequestParam(name = "cantidad", defaultValue = "1") int cantidad,
                                   Authentication authentication, RedirectAttributes redirectAttributes) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        System.out.println("Email del usuario autenticado: " + email);

        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            System.out.println("No se encontró el usuario con email: " + email);
            return "redirect:/login";
        }
        Producto producto = productoService.buscarProductoId(productoId);
        if (producto != null) {
            Venta carrito = carritoService.obtenerCarritoUsuario(usuario);
            carritoService.agregarProducto(carrito, producto, cantidad);
            redirectAttributes.addFlashAttribute("success", "Producto agregado al carrito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
        }
        return "redirect:/eShop";
    }
    @GetMapping("/carrito")
    public String verCarrito(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        // Obtener el email del usuario autenticado
        String email = authentication.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            // Manejar el caso donde el usuario no se encuentra
            return "redirect:/login";
        }

        Venta carrito = carritoService.obtenerCarritoUsuario(usuario);
        model.addAttribute("carrito", carrito);
        model.addAttribute("items", carrito.getCarritoProductos());
        model.addAttribute("total", carrito.getTotal());
        return "carrito";
    }


    @GetMapping("/eliminarDelCarrito/{productoId}")
    public String eliminarDelCarrito(@PathVariable("productoId") String productoId,
                                     Authentication authentication, RedirectAttributes redirectAttributes) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String correo = authentication.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(correo);

        Producto producto = productoService.buscarProductoId(productoId);
        if (producto != null) {
            Venta carrito = carritoService.obtenerCarritoUsuario(usuario);
            carritoService.eliminarProducto(carrito, producto);
            redirectAttributes.addFlashAttribute("success", "Producto eliminado del carrito");
        } else {
            redirectAttributes.addFlashAttribute("error", "Producto no encontrado");
        }
        return "redirect:/carrito";
    }
    @PostMapping("/finalizarCompra")
    public String finalizarCompra(Authentication authentication, RedirectAttributes redirectAttributes) {
        if (authentication == null) {
            return "redirect:/login";
        }

        String correo = authentication.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(correo);

        Venta carrito = carritoService.obtenerCarritoUsuario(usuario);

        // Aquí debes manejar la lógica de pago y validaciones
        // Por simplicidad, asumiremos que el pago es exitoso

        // Actualizamos el stock de los productos
        for (CarritoProducto item : carrito.getCarritoProductos()) {
            Producto producto = item.getProducto();
            int nuevoStock = producto.getStock() - item.getCantidad();
            if (nuevoStock < 0) {
                redirectAttributes.addFlashAttribute("error", "Stock insuficiente para el producto: " + producto.getNombre());
                return "redirect:/carrito";
            }
            producto.setStock(nuevoStock);
            productoService.guardar(producto);
        }

        // Marcamos la venta como finalizada
        carrito.setFinalizada(true);
        carrito.setFecha(new Date());
        carritoService.actualizarTotal(carrito); // Ya debería estar actualizado, pero por si acaso
        // Puedes guardar cualquier información adicional si es necesario

        redirectAttributes.addFlashAttribute("success", "Compra realizada con éxito");
        return "redirect:/inicio";
    }

}