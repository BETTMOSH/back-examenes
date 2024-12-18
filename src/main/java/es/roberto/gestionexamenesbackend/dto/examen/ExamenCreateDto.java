package es.roberto.gestionexamenesbackend.dto.examen;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Roberto Ledezma
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamenCreateDto {


    @NotEmpty
    private String titulo;
    @NotEmpty
    private String descripcion;
    private String puntosMaximos;
    private String numeroDePreguntas;
    private boolean activo = false;
    private double nota;
    @NotNull( message = "la categoria debe ir rellenada")
    private Long categoriaId;
    @NotNull (message = "el usuario debe ir rellenado")
    private Long usuarioId;
}
