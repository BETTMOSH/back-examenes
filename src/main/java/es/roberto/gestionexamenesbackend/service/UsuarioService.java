package es.roberto.gestionexamenesbackend.service;

import es.roberto.gestionexamenesbackend.entity.Usuario;
import es.roberto.gestionexamenesbackend.entity.UsuarioRol;
import es.roberto.gestionexamenesbackend.error.UsuarioFoundException;
import es.roberto.gestionexamenesbackend.repository.RolRepository;
import es.roberto.gestionexamenesbackend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;

/**
 * @author Roberto Ledezma
 */

@Slf4j
@RequiredArgsConstructor
@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    private final RolRepository rolRepository;

    // TODO: Metodo para guardar un usuario con sus roles asociados
    public Usuario saveUsuario(Usuario usuario, Set<UsuarioRol> usuarioRoles) throws Exception {
        validarUsuario(usuario);
        Optional<Usuario> usuarioLocalOptional = Optional.ofNullable(usuarioRepository.findByUsername(usuario.getUsername()));
        if (usuarioLocalOptional.isPresent()) {
            throw new UsuarioFoundException("El usuario ya está registrado");
        }

        guardarRoles(usuarioRoles);
        usuario.getUsuarioRoles().addAll(usuarioRoles);

        return usuarioRepository.save(usuario);
    }

    private void validarUsuario(Usuario usuario) {
        if (usuario == null || usuario.getUsername() == null || usuario.getUsername().isEmpty()) {
            throw new IllegalArgumentException("El usuario y su nombre de usuario no pueden estar vacíos");
        }
    }

    private void guardarRoles(Set<UsuarioRol> usuarioRoles) {
        for (UsuarioRol usuarioRol : usuarioRoles) {
            rolRepository.save(usuarioRol.getRol());
        }
    }

    public Optional<Usuario> obtenerUsuario(String username) {
        return Optional.ofNullable(usuarioRepository.findByUsername(username));
    }

    public void eliminarUsuario(Long usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("El usuario no fue encontrado");
        }
        usuarioRepository.deleteById(usuarioId);
    }

    public Usuario findByUsername(String username) {
        return usuarioRepository.findByUsername(username);
    }

    public Optional<Usuario> findByUsuarioId(Long usuarioId) {
        return usuarioRepository.findByUsuarioId(usuarioId);
    }
}