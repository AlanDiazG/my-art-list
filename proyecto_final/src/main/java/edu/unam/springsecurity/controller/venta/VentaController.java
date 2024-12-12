package edu.unam.springsecurity.controller.venta;

import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import edu.unam.springsecurity.service.venta.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j 
@Controller
@RequestMapping("/venta")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/detalle/{id}")
    public String verDetalleVenta(@PathVariable("id") Integer id, Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Acceso no autorizado a /venta/detalle/{}", id);
            return "redirect:/login";
        }

        String email = authentication.getName();
        log.info("Usuario {} solicitando detalles de la venta {}", email, id);
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            log.error("Usuario no encontrado con email: {}", email);
            return "redirect:/login";
        }

        Venta venta = ventaService.obtenerVentaPorIdYUsuario(id, usuario);

        if (venta == null) {
            log.warn("Venta con ID {} no encontrada para el usuario {}", id, email);
            model.addAttribute("error", "No se encontró la venta solicitada.");
            return "error";
        }

        log.info("Mostrando detalles de la venta {} para el usuario {}", id, email);
        model.addAttribute("venta", venta);
        model.addAttribute("items", venta.getCarritoProductos());
        return "detalle_venta";
    }
}
