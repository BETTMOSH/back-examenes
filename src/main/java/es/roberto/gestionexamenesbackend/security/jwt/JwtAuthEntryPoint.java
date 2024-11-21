package es.roberto.gestionexamenesbackend.security.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * @autor Roberto Ledezma
 */
@RequiredArgsConstructor
@Component
public class JwtAuthEntryPoint implements AuthenticationEntryPoint {

  private final ObjectMapper mapper;

  /**
   * Metodo que se ejecuta cuando un usuario no autorizado intenta acceder a un recurso protegido
   * HttpRequests y HttpResponses son los objetos que se utilizan para comunicarse con el cliente
   * HttpHeaders es una clase que representa los encabezados HTTP y sus valores de una solicitud o respuesta
   */
  @Override
  public void commence(HttpServletRequest request, HttpServletResponse response,
                       AuthenticationException authException) throws IOException {

    response.setHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    String responseBody = mapper.writeValueAsString(Map.of("message", authException.getMessage()));
    response.getWriter().write(responseBody);

  }
}
