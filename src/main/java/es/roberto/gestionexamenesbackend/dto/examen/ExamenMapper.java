package es.roberto.gestionexamenesbackend.dto.examen;

import es.roberto.gestionexamenesbackend.entity.Examen;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
/**
 * @author Roberto Ledezma
 */

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface ExamenMapper {

    /*Examen toEntity(ExamenCreateDto dto);*/
    ExamenResponseDto toDto(Examen entity);

    /*@Mapping(source = "categoriaId", target = "categoria.categoriaId")
    @Mapping(source = "usuarioId", target = "usuario.id")*/
    Examen toEntity(ExamenCreateDto dto);

    /*@Mapping(source = "categoriaId", target = "categoria.categoriaId")
    @Mapping(source = "usuarioId", target = "usuario.id")*/
    Examen toEntity(ExamenUpdateDto dto);


}
