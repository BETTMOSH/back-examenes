package es.roberto.gestionexamenesbackend.service;

import es.roberto.gestionexamenesbackend.entity.Category;
import es.roberto.gestionexamenesbackend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * @author Roberto Ledezma
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Category save(Category categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Category> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Category> findByCategoriaId(Long categoriaId) {
        return categoriaRepository.findByCategoriaId(categoriaId);
    }

    public void deleteById(Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
    }

    //init
    public Category findByTitulo(String titulo) {
        return categoriaRepository.findByTituloContainsIgnoreCase(titulo);
    }
}
