package edu.unam.springsecurity.controller.perfil;

import edu.unam.springsecurity.entities.*;
import edu.unam.springsecurity.service.carreraProfesional.CarreraProfesionalService;
import edu.unam.springsecurity.service.genero.GeneroService;
import edu.unam.springsecurity.service.tarjeta.TarjetaService;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import edu.unam.springsecurity.service.venta.VentaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
public class PerfilController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private VentaService ventaService;

    @Autowired
    private TarjetaService tarjetaService;

    @Autowired
    private GeneroService generoService;

    @Autowired
    private CarreraProfesionalService carreraProfesionalService;

    @GetMapping("/perfil")
    public String verPerfil(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Acceso no autorizado a /perfil");
            return "redirect:/login";
        }

        String email = authentication.getName();
        log.info("Accediendo al perfil del usuario {}", email);
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            log.error("Usuario no encontrado con email: {}", email);
            return "redirect:/login";
        }

        // Obtener compras anteriores (ventas finalizadas)
        List<Venta> compras = ventaService.obtenerVentasFinalizadasPorUsuario(usuario);

        // Obtener tarjetas del usuario
        List<Tarjeta> tarjetas = tarjetaService.obtenerTarjetasPorUsuario(usuario);

        model.addAttribute("usuario", usuario);
        model.addAttribute("compras", compras);
        model.addAttribute("tarjetas", tarjetas);
        return "perfil";
    }

    @GetMapping("/perfil/editar")
    public String mostrarFormularioEditarPerfil(Authentication authentication, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Acceso no autorizado a /perfil/editar");
            return "redirect:/login";
        }

        String email = authentication.getName();
        log.info("Mostrando formulario de edición de perfil para el usuario {}", email);
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            log.error("Usuario no encontrado con email: {}", email);
            return "redirect:/login";
        }

        // Obtener listas de géneros y carreras profesionales
        List<Genero> generos = generoService.obtenerTodosLosGeneros();
        List<CarreraProfesional> carreras = carreraProfesionalService.obtenerTodasLasCarreras();

        model.addAttribute("usuario", usuario);
        model.addAttribute("generos", generos);
        model.addAttribute("carreras", carreras);

        return "editar_perfil";
    }

    @PostMapping("/perfil/guardar")
    public String guardarDatosPersonales(
            Authentication authentication,
            @ModelAttribute("usuario") Usuario usuarioForm,
            Model model) {

        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Acceso no autorizado a /perfil/guardar");
            return "redirect:/login";
        }

        String email = authentication.getName();
        log.info("Usuario {} guardando cambios en su perfil", email);
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            log.error("Usuario no encontrado con email: {}", email);
            return "redirect:/login";
        }

        // Actualizar los datos del usuario
        usuario.setNombre(usuarioForm.getNombre());
        usuario.setDireccion(usuarioForm.getDireccion());
        usuario.setTelefono(usuarioForm.getTelefono());

        // Obtener el género seleccionado
        Genero genero = generoService.obtenerGeneroPorId(usuarioForm.getGenero().getId());
        usuario.setGenero(genero);

        // Obtener la carrera profesional seleccionada
        CarreraProfesional carrera = carreraProfesionalService.obtenerCarreraPorId(usuarioForm.getCarreraProfesional().getId());
        usuario.setCarreraProfesional(carrera);

        // Guardar los cambios
        usuarioService.guardar(usuario);
        log.info("Perfil del usuario {} actualizado correctamente", email);

        return "redirect:/perfil";
    }

    @GetMapping("/perfil/nuevaTarjeta")
    public String mostrarFormularioNuevaTarjeta(Model model) {
        log.info("Mostrando formulario para añadir nueva tarjeta");
        model.addAttribute("tarjeta", new Tarjeta());
        return "nueva_tarjeta";
    }

    @PostMapping("/perfil/guardarTarjeta")
    public String guardarTarjeta(Authentication authentication, @ModelAttribute Tarjeta tarjeta, Model model) {
        if (authentication == null || !authentication.isAuthenticated()) {
            log.warn("Acceso no autorizado a /perfil/guardarTarjeta");
            return "redirect:/login";
        }

        String email = authentication.getName();
        Usuario usuario = usuarioService.buscarPorCorreo(email);

        if (usuario == null) {
            log.error("Usuario no encontrado con email: {}", email);
            return "redirect:/login";
        }

        // Asignar el usuario a la tarjeta
        tarjeta.setUsuario(usuario);

        // Guardar la tarjeta
        tarjetaService.guardarTarjeta(tarjeta);
        log.info("Tarjeta guardada para el usuario {}", email);

        return "redirect:/perfil";
    }
}
