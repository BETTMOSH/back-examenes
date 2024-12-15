package es.roberto.gestionexamenesbackend.repository;

import es.roberto.gestionexamenesbackend.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;
/**
 * @author Roberto Ledezma
 */

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    public Usuario findByUsername(String username);

    Optional<Usuario> findByUsuarioId (Long usuarioId);

    // test para buscar por usuario por nota de examen
    @Query("select u from Usuario u " +
            "join fetch u.examenes e " +
            "where e.nota = ?1")
    Optional<Usuario> buscarPorNotaExamen(double nota);


    // Query para buscar por username o email
    @Query("select u from Usuario u " +
            "where lower(u.username) = ?1 or lower(u.email) = ?1")
    Optional<Usuario> buscarPorUsernameOEmail(String s);


    // Query para buscar por usuarioRol
    @Query("select u from Usuario u " +
            "join fetch u.usuarioRoles ur " +
            "join fetch ur.rol r " +
            "where r.rolNombre = ?1")
    Optional<Usuario> buscarPorRol(String rolNombre);


    // Query para buscar por examen de un usuario
    @Query("select u from Usuario u " +
            "join fetch u.examenes e " +
            "where e.examenId = ?1")
    Optional<Usuario> buscarPorExamen(Long examenId);

}
