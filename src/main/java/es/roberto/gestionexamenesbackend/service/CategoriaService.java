package es.roberto.gestionexamenesbackend.service;

import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.repository.CategoriaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public Categoria save(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> findAll() {
        return categoriaRepository.findAll();
    }

    public Optional<Categoria> findByCategoriaId(Long categoriaId) {
        return categoriaRepository.findByCategoriaId(categoriaId);
    }

    public void deleteById(Long categoriaId) {
        categoriaRepository.deleteById(categoriaId);
    }

    //init
    public Categoria findByTitulo(String titulo) {
        return categoriaRepository.findByTituloContainsIgnoreCase(titulo);
    }
}
