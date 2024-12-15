package es.roberto.gestionexamenesbackend.controller.apirest;

import es.roberto.gestionexamenesbackend.dto.pregunta.PreguntaMapper;
import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.entity.Pregunta;
import es.roberto.gestionexamenesbackend.service.ExamenService;
import es.roberto.gestionexamenesbackend.service.PreguntaService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/**
 * @author Roberto Ledezma
 */

@Slf4j
@Data
@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
@RequestMapping("/pregunta")
public class PreguntaRestController {

    private final PreguntaService preguntaService;
    private final PreguntaMapper mapper;
    private final ExamenService examenService;

    @PostMapping("/")
    public ResponseEntity<Pregunta> guardarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    /*@GetMapping("/examen/{examenId}")
    public ResponseEntity<?> listarPreguntasDelExamen(@PathVariable("examenId") Long examenId){
        Examen examen = examenService.obtenerExamen(examenId);
        Set<Pregunta> preguntas = examen.getPreguntas();

        List examenes = new ArrayList(preguntas);
        if(examenes.size() > Integer.parseInt(examen.getNumeroDePreguntas())){
            examenes = examenes.subList(0,Integer.parseInt(examen.getNumeroDePreguntas() + 1));
        }

        Collections.shuffle(examenes);
        return ResponseEntity.ok(examenes);
    }*/
    @GetMapping("/examen/{examenId}")
    public ResponseEntity<List<Pregunta>> listarPreguntasDelExamen(@PathVariable("examenId") Long examenId) {
        Examen examen = examenService.obtenerExamen(examenId);
        Set<Pregunta> preguntas = examen.getPreguntas();

        // Convertir el Set a List y asegurarse de que el tamaño no exceda el número de preguntas permitidas
        List<Pregunta> listaPreguntas = new ArrayList<>(preguntas);
        int numeroDePreguntas = Integer.parseInt(examen.getNumeroDePreguntas());

        // Limitar la lista al número de preguntas especificado
        if (listaPreguntas.size() > numeroDePreguntas) {
            listaPreguntas = listaPreguntas.subList(0, numeroDePreguntas);
        }

        // Mezclar las preguntas
        Collections.shuffle(listaPreguntas);

        return ResponseEntity.ok(listaPreguntas);
    }

    @GetMapping("/{preguntaId}")
    public Pregunta listarPreguntaPorId(@PathVariable("preguntaId") Long preguntaId){
        return preguntaService.obtenerPregunta(preguntaId);
    }

    @DeleteMapping("/{preguntaId}")
    public void eliminarPregunta(@PathVariable("preguntaId") Long preguntaId){
        preguntaService.eliminarPregunta(preguntaId);
    }

    @GetMapping("/examen/todos/{examenId}")
    public ResponseEntity<?> listarPreguntaDelExamenComoAdministrador(@PathVariable("examenId") Long examenId){
        Examen examen = new Examen();
        examen.setExamenId(examenId);
        Set<Pregunta> preguntas = preguntaService.obtenerPreguntasDelExamen(examen);
        return ResponseEntity.ok(preguntas);
    }

    @PostMapping("/evaluar-examen")
    public ResponseEntity<?> evaluarExamen(@RequestBody List<Pregunta> preguntas){
        double puntosMaximos = 0;
        Integer respuestasCorrectas = 0;
        int respuestasIncorrectas = 0;
        Integer intentos = 0;
        Examen examen = null;

        for (Pregunta p : preguntas) {
            Pregunta pregunta = this.preguntaService.listarPregunta(p.getPreguntaId());
            if (examen == null) {
                examen = pregunta.getExamen();
            }
        //Comparamos la respuesta dada por el usuario con la respuesta correcta de la pregunta
            if(pregunta.getRespuesta().equals(p.getRespuestaDada())){
                respuestasCorrectas ++;
                //En esta variable se guardan los puntos máximos de cada pregunta
                double puntos = Double.parseDouble(preguntas.get(0).getExamen().getPuntosMaximos())/preguntas.size();
                puntosMaximos += puntos;
            }else {
                if (p.getRespuestaDada() != null) {
                    respuestasIncorrectas++;
                }
            }
            if(p.getRespuestaDada() != null){
                intentos ++;
            }
        }
        //Si el examen no es nulo, guardamos la nota en la base de datos
        if (examen != null) {
            examen.setNota(puntosMaximos);
            this.examenService.save(examen);
        }

        //Guardamos los resultados en un mapa para devolverlos de forma ordenada en el response entity
        Map<String,Object> respuestas = new HashMap<>();
        respuestas.put("puntosMaximos",puntosMaximos);
        respuestas.put("respuestasCorrectas",respuestasCorrectas);
        respuestas.put("respuestasIncorrectas",respuestasIncorrectas);
        respuestas.put("intentos",intentos);
        return ResponseEntity.ok(respuestas);
    }
}
