package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
/**
 * @author Roberto Ledezma
 */

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {

    Optional<UsuarioRol> findByUsuarioNombre(String nombre);
}
