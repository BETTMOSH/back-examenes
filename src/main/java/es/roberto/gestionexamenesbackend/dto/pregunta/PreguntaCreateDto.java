package es.roberto.gestionexamenesbackend.dto.pregunta;

import es.roberto.gestionexamenesbackend.entity.Examen;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaCreateDto {

    private Long preguntaId;
    @NotEmpty
    private String contenido;
    @NotEmpty
    private String opcion1;
    @NotEmpty
    private String opcion2;
    @NotEmpty
    private String opcion3;
    @NotEmpty
    private String opcion4;
    @NotEmpty
    private String respuesta;

    private Examen examen;
}