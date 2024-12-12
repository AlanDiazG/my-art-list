package edu.unam.springsecurity.security.jwt;

import edu.unam.springsecurity.security.dto.CredentialsDTO;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private final JWTTokenProvider tokenProvider;

    public JWTAuthenticationFilter(JWTTokenProvider tokenProvider) {
        this.tokenProvider = tokenProvider;
    }

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        String jwt = "";
        if(request.getCookies() != null)
            for(Cookie cookie: request.getCookies())
                if(cookie.getName().equals("token"))
                    jwt = cookie.getValue();
        if(jwt == null || jwt.equals("")){
            filterChain.doFilter(request, response);
            return;
        }
        try {
            // Dentro del método doFilterInternal
            if (tokenProvider.validateJwtToken(jwt)) {
                Claims body = tokenProvider.getClaims(jwt);

                String email = body.get("email", String.class);
                String fullName = body.get("fullName", String.class);

                // Obtener las autoridades
                List<String> roles = body.get("roles", List.class);
                Set<SimpleGrantedAuthority> authorities = roles.stream()
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toSet());

                // Crea la autenticación
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        email, null, authorities);
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }

        } catch (Exception exception) {
            log.error("Can NOT set user authentication -> Message: {}", exception.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}
