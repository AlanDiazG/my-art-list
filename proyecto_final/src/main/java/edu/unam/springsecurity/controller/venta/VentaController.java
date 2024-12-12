package edu.unam.springsecurity.controller.venta;

import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import edu.unam.springsecurity.service.venta.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
            return "redirect:/login";
        }

        String email = authentication.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            return "redirect:/login";
        }

        Venta venta = ventaService.obtenerVentaPorIdYUsuario(id, usuario);

        if (venta == null) {
            model.addAttribute("error", "No se encontró la venta solicitada.");
            return "error";
        }

        model.addAttribute("venta", venta);
        model.addAttribute("items", venta.getCarritoProductos());
        return "detalle_venta";
    }
}
