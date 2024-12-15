package es.roberto.gestionexamenesbackend.dto.examen;

import es.roberto.gestionexamenesbackend.dto.categoria.CategoriaResponseDto;
import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Pregunta;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;
/**
 * @author Roberto Ledezma
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExamenResponseDto {

    private Long examenId;
    private String titulo;
    private String descripcion;
    private String puntosMaximos;
    private String numeroDePreguntas;
    private boolean activo;
    private Categoria categoria;  // Información de la categoría
    private Set<Pregunta> preguntas; // Información de las preguntas
    private Usuario usuario; // Información del usuario
}
