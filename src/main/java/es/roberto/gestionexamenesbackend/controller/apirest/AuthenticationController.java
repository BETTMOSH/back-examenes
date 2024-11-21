package es.roberto.gestionexamenesbackend.controller.apirest;

import es.roberto.gestionexamenesbackend.jwt.JwtSigninRequest;
import es.roberto.gestionexamenesbackend.jwt.JwtSigninResponse;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import es.roberto.gestionexamenesbackend.repository.UsuarioRepository;
import es.roberto.gestionexamenesbackend.security.UserDetailsServiceImpl;
import es.roberto.gestionexamenesbackend.security.jwt.JwtTokenProvider;
import es.roberto.gestionexamenesbackend.service.TokenBlackListService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;

/**
 * @autor Roberto
 */

@Slf4j
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthenticationController {


    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final JwtTokenProvider jwtUtils;
    private final UsuarioRepository usuarioRepository;
    private final TokenBlackListService tokenBlacklistService;
    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Método para generar el token de autenticación a partir de un usuario y una contraseña
     * UserDetailsService es una interfaz que se utiliza para recuperar información del usuario
     * ResponseEntity es una clase que representa toda la respuesta HTTP: código de estado, encabezados y cuerpo
     */
    @PostMapping("/generate-token")
    public ResponseEntity<?> generarToken(@RequestBody JwtSigninRequest jwtRequest) throws Exception {
        try{
            autenticar(jwtRequest.getUsername(),jwtRequest.getPassword());
        }catch (BadCredentialsException exception){
            log.error("Error al autenticar el usuario: {}", exception.getMessage(), exception);
            throw new Exception("Usuario no encontrado");
        }

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(jwtRequest.getUsername());
        String token = this.jwtUtils.generateToken(userDetails);

        // Para obtener el username y los roles del usuario se utiliza el método getAuthorities()
        String username = userDetails.getUsername();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        // Crear la respuesta con todos los valores
        JwtSigninResponse response = new JwtSigninResponse(token, username, roles);

        return ResponseEntity.ok(response);
    }

    /**
     * Método para autenticar un usuario a partir de un username y un password
     * AuthenticationManager es una interfaz que se utiliza para autenticar a un usuario en el sistema de seguridad
     */
    // TODO: Implementar el método autenticar que recibe un username y un password y lanza una excepción si no se puede autenticar
    private void autenticar(String username,String password) throws Exception {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
        }catch (DisabledException exception){
            throw  new Exception("USUARIO DESHABILITADO " + exception.getMessage());
        }catch (BadCredentialsException e){
            throw  new Exception("Credenciales invalidas " + e.getMessage());
        }
    }

    /**
     * Método para obtener el usuario actual
     * Principal es una interfaz que representa la identidad de un usuario autenticado
     */
    // TODO: Implementar el método para obtener el usuario actual
    @GetMapping("/actual-usuario")
    public Usuario obtenerUsuarioActual(Principal principal) {
        if (principal == null) {
            return null;
        }
        return usuarioRepository.findByUsername(principal.getName());

    }

    /*@GetMapping("/actual-usuario")
    public ResponseEntity<String> obtenerUsuarioActual(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No hay usuario autenticado");
        }
        String username = principal.getName();
        return ResponseEntity.ok("Usuario autenticado: " + username);

    }*/

    /**
     * Método que invalida el token del usuario
     */
    /*@PostMapping("/logout")
    public ResponseEntity<?> logout(@RequestHeader("Authorization") String tokenHeader) {
        String token = tokenHeader.replace("Bearer ", "");
        long expirationTime = jwtTokenProvider.getExpirationDateFromToken(token).getTime() - System.currentTimeMillis();
        tokenBlacklistService.addTokenToBlackList(token, expirationTime);
        return ResponseEntity.ok("Logout successful");
    }*/
}
