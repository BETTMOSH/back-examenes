package es.roberto.gestionexamenesbackend.dto.examen;

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
public class ExamenUpdateDto {

    private String titulo;
    private String descripcion;
    private String puntosMaximos;
    private String numeroDePreguntas;
    private boolean activo;
    private Long categoriaId;
}
