package es.roberto.gestionexamenesbackend.dto.usuario;

import es.roberto.gestionexamenesbackend.entity.Rol;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import es.roberto.gestionexamenesbackend.entity.UsuarioRol;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
/**
 * @author Roberto Ledezma
 */

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface UsuarioMapper {


    Usuario toEntity(UsuarioSignupDto dto);

    List<RolSignupDto> toDtoList(List<Rol> list);

    List<UsuarioRol> toEntityList(List<RolSignupDto> list);

    //@Mapping(target = "rol.usuarios", ignore = true)
    RolSignupDto toDto(UsuarioRol entity);

    UsuarioRol toEntity(RolSignupDto dto);
}
