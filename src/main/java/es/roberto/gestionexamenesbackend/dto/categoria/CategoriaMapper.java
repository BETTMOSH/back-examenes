package es.roberto.gestionexamenesbackend.dto.categoria;

import es.roberto.gestionexamenesbackend.entity.Categoria;
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
public interface CategoriaMapper {

    CategoriaResponseDto toDto(Categoria entity);
    Categoria toEntity(CategoriaCreateDto dto);
    Categoria toEntity(CategoriaUpdateDto dto);

    List<CategoriaResponseDto> toDtoList(List<Categoria> list);
}
