package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
/**
 * @author Roberto Ledezma
 */

public interface ExamenRepository extends JpaRepository<Examen, Long> {

    List<Examen> findByCategoria(Categoria categoria);

    List<Examen> findByActivo(Boolean estado);

    List<Examen> findByCategoriaAndActivo(Categoria categoria,Boolean estado);

    Optional<Examen> findByExamenId (Long examenId);

    Examen findByTituloContainsIgnoreCase(String titulo);



}
