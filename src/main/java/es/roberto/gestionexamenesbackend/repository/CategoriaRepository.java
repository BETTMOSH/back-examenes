package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Examen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * @author Roberto Ledezma
 */

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Override
    void deleteById(Long categoriaId);

    Optional<Categoria> findByCategoriaId(Long categoriaId);

    Categoria findByTituloContainsIgnoreCase(String titulo);



}
