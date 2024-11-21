package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.dto.usuario.RolSignupDto;
import es.roberto.gestionexamenesbackend.entity.Rol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByRolNombre(String rolNombre);

}
