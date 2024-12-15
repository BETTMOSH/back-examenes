package es.roberto.gestionexamenesbackend.controller.apirest;

import es.roberto.gestionexamenesbackend.dto.examen.ExamenCreateDto;
import es.roberto.gestionexamenesbackend.dto.examen.ExamenMapper;
import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import es.roberto.gestionexamenesbackend.service.CategoriaService;
import es.roberto.gestionexamenesbackend.service.ExamenService;
import es.roberto.gestionexamenesbackend.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/**
 * @author Roberto Ledezma
 */

@Slf4j
@Data
@RestController
@CrossOrigin("*")
@RequestMapping("/examen")
@RequiredArgsConstructor
public class ExamenRestController {

    private final ExamenService examenService;
    private final ExamenMapper mapper;
    private final CategoriaService categoriaService;
    private final UsuarioService usuarioService;

    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> addExamen(@Valid @RequestBody ExamenCreateDto examenDto) {
        try {
            /*TODO: Mapeo del DTO a la entidad Examen*/
            Examen examen = mapper.toEntity(examenDto);

            /*TODO: Obtenemos y asignamos la entidad Categoria a partir del ID*/
            Categoria categoria = categoriaService.findByCategoriaId(examenDto.getCategoriaId())
                    .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));
            examen.setCategoria(categoria);

            /*TODO: Obtenemos y asignamos la entidad Usuario a partir del ID*/
            Usuario usuario = usuarioService.findByUsuarioId(examenDto.getUsuarioId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            examen.setUsuario(usuario);

            /*TODO: Guardamos el examen y devolvemos el ID del examen guardado*/
            Long examenId = examenService.save(examen).getExamenId();
            return new ResponseEntity<>(Collections.singletonMap("id", examenId), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para actualizar un examen
     * Este método recibe un objeto de tipo Examen y lo actualiza
     * @RequestBody es un método de controlador que recibe un objeto en el cuerpo de la solicitud HTTP
     */
    @PutMapping(value = "/")
    public ResponseEntity<Examen> actualizarExamen(@RequestBody Examen examen) {
        try {
            Examen examenActualizado = examenService.actualizarExamen(examen);
            return ResponseEntity.ok(examenActualizado);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para obtener un examen por su ID
     */
    @GetMapping("/{examenId}")
    public Examen listarExamen(@Valid @PathVariable("examenId") Long examenId){
        if (examenId == null) { //Aumentamos este if para validar que el ID no sea nulo y @Valid para validar que sea un valor válido
            throw new RuntimeException("El ID del examen no puede ser nulo");
        }
        return examenService.obtenerExamen(examenId);
    }

    /**
     * Método para listar todos los exámenes
     */
    @GetMapping("/")
    public ResponseEntity<?> listarExamenes(){
        return ResponseEntity.ok(examenService.obtenerExamenes());
    }

    /**
     * Método para eliminar un examen por su ID
     */
    @DeleteMapping("/{examenId}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable("examenId") Long examenId) {
        try {
            Optional<Examen> examen = examenService.findByExamenId(examenId);
            if (examen.isPresent()) {
                examenService.deleteById(examenId);
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para listar los exámenes de una categoría
     */
    @GetMapping("/categoria/{categoriaId}")
    public List<Examen> listarExamenesDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return examenService.listarExamenesDeUnaCategoria(categoria);
    }

    /**
     * Método para listar los exámenes activos
     */
    @GetMapping("/activo")
    public List<Examen> listarExamenesActivos(){
        return examenService.findExamenesActivos();
    }

    /**
     * Método para listar los exámenes activos de una categoría
     */
    @GetMapping("/categoria/activo/{categoriaId}")
    public List<Examen> listarActivosDeUnaCategoria(@PathVariable("categoriaId") Long categoriaId){
        Categoria categoria = new Categoria();
        categoria.setCategoriaId(categoriaId);
        return examenService.findExamActivCategoria(categoria);
    }
}
