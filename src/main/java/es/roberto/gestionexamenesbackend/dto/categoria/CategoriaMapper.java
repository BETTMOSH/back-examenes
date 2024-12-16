package es.roberto.gestionexamenesbackend.dto.categoria;

import es.roberto.gestionexamenesbackend.entity.Category;
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

    CategoriaResponseDto toDto(Category entity);
    Category toEntity(CategoriaCreateDto dto);
    Category toEntity(CategoriaUpdateDto dto);

    List<CategoriaResponseDto> toDtoList(List<Category> list);
}
