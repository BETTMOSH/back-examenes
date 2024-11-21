package es.roberto.gestionexamenesbackend.jwt;

import es.roberto.gestionexamenesbackend.entity.Usuario;
import org.mapstruct.CollectionMappingStrategy;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring",
        collectionMappingStrategy = CollectionMappingStrategy.ADDER_PREFERRED
)
public interface JwtMapper {
    JwtSignupResponse toDto(Usuario entity);
    Usuario toEntity(JwtSignupRequest dto);
    JwtSigninRequest toDtoSigninRequest(Usuario entity);
    JwtSigninResponse toDtoSigninResponse(String token);

}
