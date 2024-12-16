package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.Category;
import es.roberto.gestionexamenesbackend.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * @author Roberto Ledezma
 */

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findByCategoria(Category categoria);

    List<Examen> findByActivo(Boolean estado);

    List<Examen> findByCategoriaAndActivo(Category categoria,Boolean estado);

    Optional<Examen> findByExamenId (Long examenId);

    Examen findByTituloContainsIgnoreCase(String titulo);



}
