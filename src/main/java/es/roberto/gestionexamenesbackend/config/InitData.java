package es.roberto.gestionexamenesbackend.config;

import es.roberto.gestionexamenesbackend.entity.*;

import es.roberto.gestionexamenesbackend.service.CategoriaService;
import es.roberto.gestionexamenesbackend.service.ExamenService;
import es.roberto.gestionexamenesbackend.service.PreguntaService;
import es.roberto.gestionexamenesbackend.service.UsuarioService;
import es.roberto.gestionexamenesbackend.storage.StorageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@Component
public class InitData {

    private final StorageService storageService;
    private final UsuarioService usuarioService;
    private final PasswordEncoder passwordEncoder;

    private final CategoriaService categoriaService;
    private final ExamenService examenService;
    private final PreguntaService preguntaService;


    @Transactional
    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent event) {
        initStorage();
        initUsuarios();
        /*initExamen();*/

    }

    public void initStorage() {
        storageService.deleteAll();
        storageService.init();
    }

    public void initUsuarios() {
        try {
            Usuario usuario = new Usuario();
            usuario.setUsuarioId(1L);
            usuario.setNombre("Roberto Carlos");
            usuario.setApellido("Ledezma");
            usuario.setUsername("bettmosh");
            usuario.setPassword(passwordEncoder.encode("12345"));
            usuario.setEmail("bettmosh@gmail.com");
            usuario.setFechaNacimiento(LocalDate.of(1982, 8, 8));
            usuario.setTelefono("918212020");

            // creamos un objeto de tipo Rol
            Rol rol = new Rol();
            rol.setRolId(1L);
            rol.setRolNombre("ADMIN");

            // aqui creamos un set de roles para el usuario
            Set<UsuarioRol> usuariosRoles = new HashSet<>();
            // creamos un objeto de tipo UsuarioRol
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setRol(rol);
            usuarioRol.setUsuario(usuario);
            // agregamos el rol al usuario
            usuariosRoles.add(usuarioRol);

            Usuario usuarioGuardado = usuarioService.saveUsuario(usuario, usuariosRoles);
            System.out.println("Nombre de Adminitrador: " + usuarioGuardado.getUsername());
            System.out.println("Fecha de nacimiento configurada: " + usuario.getFechaNacimiento());
        } catch (Exception e) {
            System.out.println("Error al inicializar el usuario: " + e.getMessage());
        }








        /*try {
            Usuario usuario1 = new Usuario();
            usuario1.setId(2L);
            usuario1.setNombre("Carlos");
            usuario1.setApellido("perez");
            usuario1.setUsername("carlos");
            usuario1.setPassword(passwordEncoder.encode("1234"));
            usuario1.setEmail("carlos@gmail.com");
            usuario1.setFechaNacimiento(LocalDate.of(1982, 1, 1));
            usuario1.setTelefono("918212022");

            Rol rol = new Rol();
            rol.setRolId(2L);
            rol.setRolNombre("NORMAL");

            // aqui creamos un set de roles para el usuario
            Set<UsuarioRol> usuariosNormal = new HashSet<>();
            // aqui creamos un objeto de tipo UsuarioRol
            UsuarioRol usuarioRol = new UsuarioRol();
            usuarioRol.setRol(rol);
            usuarioRol.setUsuario(usuario1);
            // aqui agregamos el rol al usuario
            usuariosNormal.add(usuarioRol);

            Usuario usuarioGuardado = usuarioService.saveUsuario(usuario1, usuariosNormal);
            System.out.println("Nombre de Adminitrador: " + usuarioGuardado.getUsername());
            System.out.println("Fecha de nacimiento configurada: " + usuario1.getFechaNacimiento());
        } catch (Exception e) {
            System.out.println("Error al inicializar el usuario: " + e.getMessage());
        }


    }

    public void initExamen(){
        Examen examen1 = Examen.builder()
                .titulo("Matematicas basico")
                .descripcion("Examen de matematicas basico")
                .puntosMaximos("100")
                .numeroDePreguntas("5")
                .activo(true)
                .preguntas(Set.of())
                .usuario(usuarioService.findByUsername("carlos"))
                .categoria(categoriaService.findByTitulo("Matematicas"))
                .build();

        examenService.save(examen1);


        Pregunta pregunta1 = Pregunta.builder()
                .preguntaId(1L)
                .contenido("Cual es el resultado de 2+2")
                .opcion1("1")
                .opcion2("2")
                .opcion3("3")
                .opcion4("4")
                .respuestaDada("4")
                .respuesta("4")
                .examen(examen1)
                .build();

        preguntaService.agregarPregunta(pregunta1);


        Categoria categoria1 = Categoria.builder()
                .titulo("Matematicas")
                .descripcion("Preguntas de matematicas")
                .examenes(Set.of(examen1))
                .build();

        categoriaService.save(categoria1);

    }*/

    }
}

