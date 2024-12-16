package es.roberto.gestionexamenesbackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
/**
 * @author Roberto Ledezma
 */
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "examenes")
public class Examen {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long examenId;
    private String titulo;
    private String descripcion;
    @NotNull
    private String puntosMaximos;
    @NotNull
    private String numeroDePreguntas;
    private boolean activo = false;
    private double nota;

    @ManyToOne
    @JoinColumn(name = "categoria_id",nullable = false)
    private Category categoria;

    @OneToMany(mappedBy = "examen")
    @JsonIgnore
    private Set<Pregunta> preguntas = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Usuario usuario;


}
