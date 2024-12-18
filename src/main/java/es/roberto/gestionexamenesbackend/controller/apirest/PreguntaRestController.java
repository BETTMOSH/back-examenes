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
        if (pregunta.getRespuestaDada() == null) {
            pregunta.setRespuestaDada(""); // Valor predeterminado si no se envía
        }
        return ResponseEntity.ok(preguntaService.agregarPregunta(pregunta));
    }

    @PutMapping("/")
    public ResponseEntity<Pregunta> actualizarPregunta(@RequestBody Pregunta pregunta){
        return ResponseEntity.ok(preguntaService.actualizarPregunta(pregunta));
    }

    @GetMapping("/examen/{examenId}")
    public ResponseEntity<List<Pregunta>> listarPreguntasDelExamen(@PathVariable("examenId") Long examenId) {
        Examen examen = examenService.obtenerExamen(examenId);
        Set<Pregunta> preguntas = examen.getPreguntas();

        // TODO: Convertir el Set a List para poder manipular los datos sin repetir
        List<Pregunta> listaPreguntas = new ArrayList<>(preguntas);
        int numeroDePreguntas = Integer.parseInt(examen.getNumeroDePreguntas());

        // TODO: Verificamos si el número de preguntas es mayor que el número de preguntas permitidas
        if (listaPreguntas.size() > numeroDePreguntas) {
            listaPreguntas = listaPreguntas.subList(0, numeroDePreguntas);
        }

        //TODO: Mezclar las preguntas para que no se muestren siempre en el mismo orden
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
        //TODO: Comparamos la respuesta dada por el usuario con la respuesta correcta de la pregunta
            if(pregunta.getRespuesta().equals(p.getRespuestaDada())){
                respuestasCorrectas ++;
                //TODO: En esta variable se guardan los puntos máximos de cada pregunta sin repetir
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
        //TODO: Si el examen no es nulo, guardamos la nota en la base de datos
        if (examen != null) {
            examen.setNota(puntosMaximos);
            this.examenService.save(examen);
        }

//        TODO: Guardamos los resultados en un mapa para devolverlos de forma ordenada en el response entity
        Map<String,Object> respuestas = new HashMap<>();
        respuestas.put("puntosMaximos",puntosMaximos);
        respuestas.put("respuestasCorrectas",respuestasCorrectas);
        respuestas.put("respuestasIncorrectas",respuestasIncorrectas);
        respuestas.put("intentos",intentos);
        respuestas.put("examen",examen);
        return ResponseEntity.ok(respuestas);
    }
}
