package es.roberto.gestionexamenesbackend.jwt;

import es.roberto.gestionexamenesbackend.entity.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtSignupRequest {
  @NotBlank
  @Size(min = 3, max = 20)
  private String username;

  @NotBlank
  @Size(min = 6, max = 40)
  private String password;

  private String nombre;

  private String apellido;

  @NotNull
  private String email;

  private LocalDate fechaNacimiento;

  private String telefono;

  private Set<Rol> roles;

}
