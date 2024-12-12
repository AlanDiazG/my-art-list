package edu.unam.springsecurity.controller.pago;

import edu.unam.springsecurity.entities.Tarjeta;
import edu.unam.springsecurity.entities.Usuario;
import edu.unam.springsecurity.entities.Venta;
import edu.unam.springsecurity.security.model.UserDetailsImpl;
import edu.unam.springsecurity.service.carrito.CarritoService;
import edu.unam.springsecurity.service.tarjeta.TarjetaService;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import edu.unam.springsecurity.service.venta.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class PagoController {

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private CarritoService carritoService;

    @Autowired
    private VentaService ventaService;

    @GetMapping("/pago")
    public String mostrarOpcionesDePago(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            return "redirect:/login";
        }

        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasPorUsuario(usuario);
        Venta carrito = carritoService.obtenerCarritoUsuario(usuario);

        model.addAttribute("tarjetas", tarjetas);
        model.addAttribute("total", carrito.getTotal());
        return "pago";
    }

    @PostMapping("/procesarPago")
    public String procesarPago(Authentication authentication,
                               @RequestParam("tarjetaId") Integer tarjetaId,
                               Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "redirect:/login";
        }

        String email = authentication.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            return "redirect:/login";
        }

        Tarjeta tarjeta = tarjetaService.obtenerTarjetaPorIdYUsuario(tarjetaId, usuario);

        if (tarjeta == null) {
            model.addAttribute("error", "Tarjeta no encontrada.");
            return "pago";
        }

        Venta carrito = carritoService.obtenerCarritoUsuario(usuario);
        Float total = (float) carrito.getTotal();

        if (tarjeta.getSaldo() < total) {
            model.addAttribute("error", "Saldo insuficiente en la tarjeta.");
            return "pago";
        }

        // Actualizar el saldo de la tarjeta
        tarjetaService.actualizarSaldo(tarjeta, tarjeta.getSaldo() - total);

        // Marcar la venta como finalizada
        carrito.setFinalizada(true);
        ventaService.guardarVenta(carrito);

        // Crear un nuevo carrito para el usuario (opcional)
        carritoService.crearNuevoCarritoParaUsuario(usuario);

        model.addAttribute("mensaje", "Pago realizado con éxito.");
        return "pago_exitoso";
    }
}
