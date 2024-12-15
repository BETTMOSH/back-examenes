package es.roberto.gestionexamenesbackend.dto.usuario;

import jakarta.validation.constraints.NotNull;
/**
 * @author Roberto Ledezma
 */

public record RolSignupDto (
    Long usuarioRolId,
    @NotNull
    String nombre


){}
