package es.roberto.gestionexamenesbackend.controller.apirest;

import es.roberto.gestionexamenesbackend.dto.categoria.CategoriaCreateDto;
import es.roberto.gestionexamenesbackend.dto.categoria.CategoriaMapper;
import es.roberto.gestionexamenesbackend.dto.categoria.CategoriaResponseDto;
import es.roberto.gestionexamenesbackend.dto.categoria.CategoriaUpdateDto;
import es.roberto.gestionexamenesbackend.entity.Category;
import es.roberto.gestionexamenesbackend.service.CategoriaService;
import jakarta.validation.Valid;
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
@RestController
@RequestMapping("/categoria")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CategoriaRestController {

    private final CategoriaService categoriaService;
    private final CategoriaMapper mapper;

    /**
     * Método para guardar una nueva categoría
     * ResponseEntity es una clase que representa toda la respuesta HTTP: código de estado, encabezados y cuerpo
     */
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> addCategoria(@Valid @RequestBody CategoriaCreateDto categDto) {
        if (categDto == null)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        try {
            Long categoriaId = categoriaService.save(mapper.toEntity(categDto)).getCategoriaId();
            return new ResponseEntity<>(Collections.singletonMap("categoriaId", categoriaId), HttpStatus.CREATED);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para obtener todas las categorías
     * List<CategoriaResponseDto> es una lista de objetos de tipo Dto de respuesta de categoría (CategoriaResponseDto)
     */
    @GetMapping("/")
    public ResponseEntity<List<CategoriaResponseDto>> getAll() {
        try {
            List<CategoriaResponseDto> categoria = mapper.toDtoList(categoriaService.findAll());
            return new ResponseEntity<>(categoria, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para obtener una categoría por su ID
      */
    @GetMapping("/{categoriaId}")
    public ResponseEntity<CategoriaResponseDto> getOne(@PathVariable("categoriaId") Long categoriaId){
        Optional<Category> categoria = categoriaService.findByCategoriaId(categoriaId);

        return categoria
                .map(m -> new ResponseEntity<>(mapper.toDto(m), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(null, HttpStatus.NOT_FOUND));
    }

    /**
     * Método para actualizar una categoría
     */
    @PutMapping("/{categoriaId}")
    public ResponseEntity<Map<String, Object>> updateReplace(@Valid @RequestBody(required = false) CategoriaUpdateDto cateDto,
                                                             @PathVariable("categoriaId") Long categoriaId) {
        if (cateDto == null)
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        try {
            Optional<Category> categoriaDB = categoriaService.findByCategoriaId(categoriaId);
            if (categoriaDB.isPresent()) {
                categoriaService.save(mapper.toEntity(cateDto));
                return new ResponseEntity<>(null, HttpStatus.OK);
            } else
                return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Método para eliminar una categoría
     */
    @DeleteMapping("/{categoriaId}")
    public ResponseEntity<Map<String, Object>> deleteCategoria(@PathVariable("categoriaId") Long categoriaId) {
        try {
            categoriaService.deleteById(categoriaId);
            return new ResponseEntity<>(Collections.singletonMap("categoriaId", categoriaId), HttpStatus.OK);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity<>(Collections.singletonMap("error", e.getMessage()),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
