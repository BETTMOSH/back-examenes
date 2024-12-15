package es.roberto.gestionexamenesbackend.dto.pregunta;

import es.roberto.gestionexamenesbackend.entity.Pregunta;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

import java.util.List;
/**
 * @author Roberto Ledezma
 */

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface PreguntaMapper {

    PreguntaResponseDto toResponseDto(Pregunta pregunta);
    Pregunta toEntity(PreguntaCreateDto preguntaRequestDto);
    PreguntaCreateDto toDto(Pregunta pregunta);

    List<PreguntaCreateDto> toDoList(List<Pregunta> list);
    // Si necesitas convertir de ResponseDto a entidad o viceversa, puedes agregar métodos adicionales
}

