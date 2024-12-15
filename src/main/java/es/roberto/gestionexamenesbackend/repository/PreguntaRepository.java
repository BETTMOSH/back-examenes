package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.entity.Pregunta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;
/**
 * @author Roberto Ledezma
 */

public interface PreguntaRepository extends JpaRepository<Pregunta, Long> {

    Set<Pregunta> findByExamen(Examen examen);

    Pregunta findByRespuesta(String contenido);

    // query methods
    Set<Pregunta> findByExamenAndRespuesta(Examen examen, String respuesta);
}
