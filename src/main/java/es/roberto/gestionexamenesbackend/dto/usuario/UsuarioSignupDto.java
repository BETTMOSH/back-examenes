package es.roberto.gestionexamenesbackend.dto.usuario;

import es.roberto.gestionexamenesbackend.entity.Rol;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;
import java.util.Set;
/**
 * @author Roberto Ledezma
 */

@Builder
public record UsuarioSignupDto (
        @NotNull
        String username,
        @NotNull
        String email,
        @NotNull
        String password,
        @NotNull
        String nombre,
        @NotNull
        String apellidos,

        LocalDate fechaNacimiento,
        @NotNull
        String telefono,
        @NotNull
        Set<Rol>roles
) { }


