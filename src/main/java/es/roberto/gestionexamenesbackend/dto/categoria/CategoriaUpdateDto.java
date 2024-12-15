package es.roberto.gestionexamenesbackend.dto.categoria;

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
public class CategoriaUpdateDto {

    private Long categoriaId;
    private String titulo;
    private String descripcion;
}
