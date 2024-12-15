package es.roberto.gestionexamenesbackend.service;

import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.repository.ExamenRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
/**
 * @author Roberto Ledezma
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class ExamenService {
    private final ExamenRepository examenRepository;

    public Examen agregarExamen(Examen examen) {
        return examenRepository.save(examen);
    }


    public Examen actualizarExamen(Examen examen) {
        return examenRepository.save(examen);
    }

    // LinkkenHashSet para mantener el orden de insercion y evitar duplicados
    public Set<Examen> obtenerExamenes() {
        return new LinkedHashSet<>(examenRepository.findAll());
    }

    @Transactional
    // instanciado en el controlador de PreguntaRestController
    public Examen obtenerExamen(Long examenId) {
        return examenRepository.findById(examenId).get();
    }


    public List<Examen> listarExamenesDeUnaCategoria(Categoria categoria) {
        return this.examenRepository.findByCategoria(categoria);
    }


    public List<Examen> findExamenesActivos() {
        return examenRepository.findByActivo(true);
    }


    public List<Examen> findExamActivCategoria(Categoria categoria) {
        return examenRepository.findByCategoriaAndActivo(categoria,true);
    }



    public Examen save(Examen examen) {
        return examenRepository.save(examen);
    }

    public List<Examen> findAll() {
        return examenRepository.findAll();
    }

    public Optional<Examen> findByExamenId(Long examenId) {
        return examenRepository.findByExamenId(examenId);
    }

    public void deleteById(Long examenId) {
        examenRepository.deleteById(examenId);
    }

    //para el init
    /*public void addCategoria(Examen examen, Categoria categoria) {
        examen.setCategoria(categoria);
        examenRepository.save(examen);
    }*/
}