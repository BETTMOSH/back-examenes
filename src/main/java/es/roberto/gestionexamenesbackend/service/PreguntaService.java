package es.roberto.gestionexamenesbackend.service;

import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.entity.Pregunta;
import es.roberto.gestionexamenesbackend.repository.PreguntaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
/**
 * @author Roberto Ledezma
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class PreguntaService {

    private final PreguntaRepository preguntaRepository;


    public Pregunta agregarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }


    public Pregunta actualizarPregunta(Pregunta pregunta) {
        return preguntaRepository.save(pregunta);
    }


    public Set<Pregunta> findAll() {
        return (Set<Pregunta>) preguntaRepository.findAll();
    }


    public Pregunta obtenerPregunta(Long preguntaId) {
        return preguntaRepository.findById(preguntaId).orElse(null);
    }


    public Set<Pregunta> obtenerPreguntasDelExamen(Examen examen) {
        return preguntaRepository.findByExamen(examen);
    }


    public void eliminarPregunta(Long preguntaId) {
        Pregunta pregunta = new Pregunta();
        pregunta.setPreguntaId(preguntaId);
        preguntaRepository.delete(pregunta);
    }


    public Pregunta listarPregunta(Long preguntaId) {
        return this.preguntaRepository.getReferenceById(preguntaId);
    }
}
