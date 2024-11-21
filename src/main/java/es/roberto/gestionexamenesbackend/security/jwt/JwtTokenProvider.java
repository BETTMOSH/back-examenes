package es.roberto.gestionexamenesbackend.security.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.crypto.SecretKey;
import java.util.Date;

/**
 * @autor Roberto
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class JwtTokenProvider {
  public static final String TOKEN_HEADER = "Authorization";
  public static final String TOKEN_PREFIX = "Bearer ";
  public static final String TOKEN_TYPE = "JWT";

  @Value("${jwt.secret}")
  private String jwtSecret;

  // Valor obtenido del application.properties
  @Value("${jwt.expiration:86400}")
  private int jwtExpiration;

  /**
   * Metodo que obtiene la clave secreta para firmar el token
   */
  private SecretKey getSigningKey() {
    return Keys.hmacShaKeyFor(jwtSecret.getBytes());
  }

  /**
   * Metodo que genera el token a partir de los datos del usuario
   */
  /*TODO: Se ha modificado el jwt bilder a una version mas reciente.*/
  public String generateToken(UserDetails userDetails) {
    return Jwts.builder()
            .subject(userDetails.getUsername()) // Usar el método moderno `subject`
            .issuedAt(new Date())
            .expiration(new Date(System.currentTimeMillis() + jwtExpiration * 1000L))
            .signWith(getSigningKey(), Jwts. SIG. HS512) // Asegúrate de que `key` sea un objeto `Key` configurado adecuadamente
            .compact();
  }

    /**
     * Metodo que obtiene el token de la cabecera de la peticion
     */
  public String getTokenFromRequest(HttpServletRequest request) {
    String bearerToken = request.getHeader(TOKEN_HEADER);
    if (StringUtils.hasText(bearerToken) && bearerToken.startsWith(TOKEN_PREFIX)) {
      return bearerToken.substring(TOKEN_PREFIX.length());
    }
    return null;
  }
  private Claims getAllClaimsFromToken(String token) {
    return Jwts.parser()
            .verifyWith(getSigningKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
  }

    /**
     * Metodo que obtiene el nombre de usuario a partir del token
     */
  public String getUsernameFromToken(String token) {
    return getAllClaimsFromToken(token).getSubject();
  }

    /**
     * Metodo que valida el token
     */
  public boolean validateToken(String authToken) {
    try {
      getAllClaimsFromToken(authToken);
      return true;
    } catch (MalformedJwtException e) {
      log.error("Token JWT malformado: {}", e.getMessage());
    } catch (ExpiredJwtException e) {
      log.error("El token JWT ha expirado: {}", e.getMessage());
    } catch (UnsupportedJwtException e) {
      log.error("Token JWT no soportado: {}", e.getMessage());
    } catch (IllegalArgumentException e) {
      log.error("JWT claims vacío: {}", e.getMessage());
    }

    return false;
  }

  /*public void invalidateToken(String token) {
    invalidateToken(token);
  }*/



  /**
   *Metodo para obtener la fecha de expiracion del token
   *
   */
  public Date getExpirationDateFromToken(String token) {
    return getAllClaimsFromToken(token).getExpiration();
  }
}
