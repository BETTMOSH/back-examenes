package es.roberto.gestionexamenesbackend.security.jwt;

import es.roberto.gestionexamenesbackend.service.TokenBlackListService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
/**
 * @autor Roberto Ledezma
 */

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtAuthFilter extends OncePerRequestFilter {

  private final JwtTokenProvider tokenProvider;
  private final UserDetailsService userDetailsService;
    private final TokenBlackListService tokenBlacklistService;

  /**
   * Método que hace la autenticacion del usuario  a partir del token que se envia en la cabecera de la peticion
   * Incorpora el token a la lista negra de Redis si el token no es valido
   */
  @Override
  protected void doFilterInternal( HttpServletRequest request,
                                   HttpServletResponse response, FilterChain filterChain)
    throws ServletException, IOException {

    String token = tokenProvider.getTokenFromRequest(request);
    if (StringUtils.hasText(token) && tokenProvider.validateToken(token)) {

      // Verificar si el token está en la lista negra
      /*if (!tokenBlacklistService.isTokenBlackListed(token)) {*/ //redis
        String username = tokenProvider.getUsernameFromToken(token);
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authentication);

      } /*else {
        log.warn("El token se ha movido a la lista negra: " + token); //redis
      }
    }*/
    filterChain.doFilter(request, response);
  }
}
