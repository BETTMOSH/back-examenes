package es.roberto.gestionexamenesbackend.jwt;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
/**
 * @author Roberto Ledezma
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtSignupResponse {
  private Long id;
  private String username;

}
