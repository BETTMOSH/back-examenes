package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author Roberto Ledezma
 */

@Repository
public interface CategoriaRepository extends JpaRepository<Category, Long> {

    @Override
    void deleteById(Long categoriaId);

    Optional<Category> findByCategoriaId(Long categoriaId);

    Category findByTituloContainsIgnoreCase(String titulo);



}
