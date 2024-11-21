package es.roberto.gestionexamenesbackend.dto.usuario;

import jakarta.validation.constraints.NotNull;

public record RolSignupDto (
    Long usuarioRolId,
    @NotNull
    String nombre


){}
