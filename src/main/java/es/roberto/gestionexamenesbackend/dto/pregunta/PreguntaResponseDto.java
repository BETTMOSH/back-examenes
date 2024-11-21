package es.roberto.gestionexamenesbackend.dto.pregunta;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PreguntaResponseDto {
    private Long preguntaId;
    private String contenido;
    private String opcion1;
    private String opcion2;
    private String opcion3;
    private String opcion4;
    private String respuesta; // Respuesta correcta
    private Long examenId; // ID del examen al que pertenece
}