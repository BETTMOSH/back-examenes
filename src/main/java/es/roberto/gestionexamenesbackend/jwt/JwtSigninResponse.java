package es.roberto.gestionexamenesbackend.jwt;

import es.roberto.gestionexamenesbackend.entity.Rol;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class JwtSigninResponse {
  private String token;
  private String username;
  private List<String> roles;


  public JwtSigninResponse(String token) {
    this.token = token;

  }
}
