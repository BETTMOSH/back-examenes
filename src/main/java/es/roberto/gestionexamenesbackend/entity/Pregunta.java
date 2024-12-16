package es.roberto.gestionexamenesbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
/**
 * @author Roberto Ledezma
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "preguntas")
public class Pregunta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preguntaId;

    @Column(length = 500)
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
    private String respuestaDada;

    private String respuesta;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonIgnore
    private Examen examen;
}
