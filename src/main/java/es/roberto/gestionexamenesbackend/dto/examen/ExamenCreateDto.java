package es.roberto.gestionexamenesbackend.dto.examen;

import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Pregunta;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;
/**
 * @author Roberto Ledezma
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamenCreateDto {

    @NotNull( message = "la categoria debe ir rellenada")
    private Long categoriaId;
    @NotEmpty
    private String titulo;
    @NotEmpty
    private String descripcion;
    private String puntosMaximos;
    private String numeroDePreguntas;
    private boolean activo = false;
    private double nota;
    @NotNull (message = "el usuario debe ir rellenado")
    private Long usuarioId;
}
