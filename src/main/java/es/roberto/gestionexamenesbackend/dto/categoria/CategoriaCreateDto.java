package es.roberto.gestionexamenesbackend.dto.categoria;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoriaCreateDto {

    @NotEmpty(message = "El titulo no puede estar vacio")
    private String titulo;

    @NotEmpty(message = "La descripcion no puede estar vacia")
    private String descripcion;

}
