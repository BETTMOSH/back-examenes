package es.roberto.gestionexamenesbackend.controller.apirest;

import es.roberto.gestionexamenesbackend.entity.Rol;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import es.roberto.gestionexamenesbackend.entity.UsuarioRol;
import es.roberto.gestionexamenesbackend.repository.RolRepository;
import es.roberto.gestionexamenesbackend.service.UsuarioService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;
/**
 * @author Roberto Ledezma
 */

@RestController
@CrossOrigin("*")
@Slf4j
@Data
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;
    private  final RolRepository rolRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/")
    public Usuario guardarUsuario(@RequestBody Usuario usuario) throws Exception{
        usuario.setPassword(this.passwordEncoder.encode(usuario.getPassword()));
        // creamos un set de roles para el usuario
        Set<UsuarioRol> usuarioRoles = new HashSet<>();

        // Verifica si el rol ya existe o créalo y guárdalo
        Rol rol = rolRepository.findById(2L).orElseGet(() -> {
            Rol nuevoRol = new Rol();
            nuevoRol.setRolId(2L);
            nuevoRol.setRolNombre("NORMAL");
            return rolRepository.save(nuevoRol);
        });

        // creamos un usuarioRol para el usuario
        UsuarioRol usuarioRol = new UsuarioRol();
        usuarioRol.setUsuario(usuario);
        usuarioRol.setRol(rol);
        // añadimos el usuarioRol al set de roles
        usuarioRoles.add(usuarioRol);
        return usuarioService.saveUsuario(usuario,usuarioRoles);
    }


    @GetMapping("/{username}")
    public Optional<Usuario> obtenerUsuario(@PathVariable("username") String username){
        return usuarioService.obtenerUsuario(username);
    }

    @DeleteMapping("/{usuarioId}")
    public void eliminarUsuario(@PathVariable("usuarioId") Long usuarioId){
        usuarioService.eliminarUsuario(usuarioId);
    }

}
