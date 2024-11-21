package es.roberto.gestionexamenesbackend.security;


import es.roberto.gestionexamenesbackend.entity.Usuario;
import es.roberto.gestionexamenesbackend.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;

    //comprobamos si el usuario existe
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = this.usuarioRepository.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        return mapUsuarioToUserDetails(usuario);
    }

    //metodo para capturar la informacion del usuario y su rol mediante el userdetails
        private UserDetails mapUsuarioToUserDetails(Usuario usuario) {
            //le pasamos al userdetails la informacion del usuario
            return org.springframework.security.core.userdetails.User.withUsername(usuario.getUsername())
                    .password(usuario.getPassword())
                    .roles(usuario.getUsuarioRoles().stream()
                            .map(r -> r.getRol().getRolNombre()).toArray(String[]::new))
                    .build();
        }

}
