package edu.unam.springsecurity.auth.controller;

import edu.unam.springsecurity.auth.dto.UserInfoDTO;
import edu.unam.springsecurity.auth.dto.UserInfoRoleDTO;
import edu.unam.springsecurity.auth.exception.UserInfoNotFoundException;
import edu.unam.springsecurity.auth.model.UserInfo;
import edu.unam.springsecurity.auth.service.UserInfoService;
import edu.unam.springsecurity.entities.*;
import edu.unam.springsecurity.security.jwt.JWTTokenProvider;
import edu.unam.springsecurity.security.request.JwtRequest;
import edu.unam.springsecurity.security.request.LoginUserRequest;
import edu.unam.springsecurity.security.service.UserDetailsServiceImpl;
import edu.unam.springsecurity.service.carreraProfesional.CarreraProfesionalService;
import edu.unam.springsecurity.service.genero.GeneroService;
import edu.unam.springsecurity.service.pintura.PinturaService;
import edu.unam.springsecurity.service.producto.ProductoService;
import edu.unam.springsecurity.service.usuario.UsuarioService;
import edu.unam.springsecurity.system.service.AdminService;
import edu.unam.springsecurity.system.service.HomeService;
import edu.unam.springsecurity.system.service.UserService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.text.SimpleDateFormat;
import java.util.*;

@Slf4j
@Controller
public class HomeController {
	private final HomeService homeService;
	private final UserService userService;
	private final AdminService adminService;
	private final UserInfoService userInfoService;
	private final AuthenticationManager authenticationManager;
	private final JWTTokenProvider jwtTokenProvider;
	private final UserDetailsServiceImpl userDetailsService;
	@Autowired
	PinturaService pinturaService;
	@Autowired
	ProductoService productoService;
	@Autowired
	UsuarioService usuarioService;
	@Autowired
	GeneroService generoService;
	@Autowired
	CarreraProfesionalService carreraProfesionalService;

	@Value("${spring.application.name}")
	String nombreApp;

	// Controller Injection
	public HomeController(HomeService homeService, UserService userService, AdminService adminService, UserInfoService userInfoService,
						  AuthenticationManager authenticationManager, JWTTokenProvider jwtTokenProvider, UserDetailsServiceImpl userDetailsService) {
		this.homeService = homeService;
		this.userService = userService;
		this.adminService = adminService;
		this.userInfoService = userInfoService;
		this.authenticationManager = authenticationManager;
		this.jwtTokenProvider = jwtTokenProvider;
		this.userDetailsService = userDetailsService;
	}

	@GetMapping("/")
	public String home(Model model) {
		model.addAttribute("text", homeService.getText());
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("nombreAplicacion",nombreApp);
		model.addAttribute("fecha",formato.format(new Date()));
		return "index";
	}

	@GetMapping("/index")
	public String index() {
		return "redirect:/";
	}

	@GetMapping("/inicio")
	public String inicio(Model model) {
		List<Pintura> pinturas = pinturaService.buscarAllPintura();
		model.addAttribute("pinturas", pinturas);
		model.addAttribute("contenido", "Bienvenido");
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		model.addAttribute("fecha", formato.format(new Date()));
		model.addAttribute("nombreAplicacion", "Seleccione alguna de las opciones para ver las listas o dar de alta.");
		return "inicio";
	}


	@GetMapping("/user")
	@PreAuthorize("hasRole('USER')")
	public String user(Model model) {
		model.addAttribute("text", userService.getText());
		return "user";
	}

	@GetMapping("/admin")
	@PreAuthorize("hasRole('ADMIN')")
	public String admin(Model model) {
		model.addAttribute("text", adminService.getText());
		return "admin";
	}

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@PostMapping("/login_success_handler")
	public String loginSuccessHandler() {
		System.out.println("Logging user login success...");
		return "index";
	}

	@PostMapping("/login_failure_handler")
	public String loginFailureHandler() {
		System.out.println("Login failure handler....");
		return "login";
	}

	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new UserInfoDTO());
		return "signup_form";
	}

	@PostMapping("/process_register")
	public String processRegister(UserInfoDTO user) throws UserInfoNotFoundException {
		// Código existente para guardar en sec_user
		user.setUseIdStatus(1);
		Set<UserInfoRoleDTO> roles = new HashSet<>();
		roles.add(UserInfoRoleDTO.builder().usrId(2L).build());
		user.setUseInfoRoles(roles);
		user.setUseCreatedBy(1L);
		user.setUseModifiedBy(1L);
		userInfoService.save(user);

		// Obtenemos el secUser
		UserInfo secUser = userInfoService.findEntityByUseEmail(user.getUseEmail());

		// Creamos el objeto Usuario y establecemos los campos requeridos
		Usuario usuario = new Usuario();
		usuario.setSecUser(secUser);
		usuario.setNombre(user.getUseFirstName() + " " + user.getUseLastName());
		usuario.setCorreo(user.getUseEmail());
		usuario.setContrasena(user.getUsePasswd()); // O bien, el password ya encriptado
		usuario.setDireccion("Dirección por defecto"); // Puedes ajustar según tus necesidades
		usuario.setTelefono("0000000000"); // Puedes ajustar según tus necesidades

		// Asignar valores por defecto o obtenerlos de algún lugar
		Genero genero = generoService.obtenerGeneroPorDefecto(); // Necesitas implementar este método
		usuario.setGenero(genero);

		CarreraProfesional carreraProfesional = carreraProfesionalService.obtenerCarreraPorDefecto(); // Implementar este método
		usuario.setCarreraProfesional(carreraProfesional);

		// Guardar el usuario
		usuarioService.guardar(usuario);

		return "register_success";
	}


	@PostMapping("/token")
	public String createAuthenticationToken(Model model, RedirectAttributes redirectAttributes,
											@ModelAttribute LoginUserRequest loginUserRequest, HttpServletResponse res) {
		log.info("LoginUserRequest {}", loginUserRequest);
		try {
			UserInfoDTO user = userInfoService.findByUseEmail(loginUserRequest.getUsername());

			if (user == null || user.getUseIdStatus() != 1) {
				// Usuario no existe o no está activo
				redirectAttributes.addFlashAttribute("errorMessage", "Invalid Username or Password");
				return "redirect:/login?error=true";
			}

			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginUserRequest.getUsername(), loginUserRequest.getPassword()));
			log.info("authentication {}", authentication);
			String jwtToken = jwtTokenProvider.generateJwtToken(authentication, user);
			log.info("jwtToken {}", jwtToken);
			JwtRequest jwtRequest = new JwtRequest(jwtToken, user.getUseId(), user.getUseEmail(),
					jwtTokenProvider.getExpiryDuration(), authentication.getAuthorities());
			log.info("jwtRequest {}", jwtRequest);
			Cookie cookie = new Cookie("token", jwtToken);
			cookie.setMaxAge(Integer.MAX_VALUE);
			res.addCookie(cookie);

			return "redirect:/index";

		} catch (AuthenticationException e) {
			// Captura todas las excepciones de autenticación
			redirectAttributes.addFlashAttribute("errorMessage", "Invalid Username or Password");
			return "redirect:/login?error=true";
		} catch (Exception e) {
			// Captura cualquier otra excepción que pueda ocurrir
			log.error("Error during authentication", e);
			redirectAttributes.addFlashAttribute("errorMessage", "The username doesn't exist");
			return "redirect:/login?error=true";
		}
	}



	private Authentication authenticate(String username, String password) throws Exception {
		try {
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw e; // Re-lanza la excepción original
		} catch (BadCredentialsException e) {
			throw e; // Re-lanza la excepción original
		}
	}

	@GetMapping("/eShop")
	public String eShop(Model model) {
		List<Producto> productos = productoService.buscarAllProducto();
		model.addAttribute("productos", productos);
		model.addAttribute("contenido", "Tienda en línea");
		return "eShop";
	}




}
