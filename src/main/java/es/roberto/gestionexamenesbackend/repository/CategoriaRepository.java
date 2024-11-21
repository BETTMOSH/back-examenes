package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;


public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    @Override
    // deletbyid
    public void deleteById(Long categoriaId);

    Optional<Categoria> findByCategoriaId(Long categoriaId);

    Categoria findByTituloContainsIgnoreCase(String titulo);

}
