package es.roberto.gestionexamenesbackend.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import es.roberto.gestionexamenesbackend.entity.Category;
import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@DBUnit
@Slf4j
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    ExamenRepository examenRepository;
    @Autowired
    CategoriaRepository categoriaRepository;


    // Test para buscar un usuario por su username o email
    @Test
    void should_FindByUsernameOrEmail() {
        Usuario usuario = Usuario.builder()
                .username("roberto")
                .nombre("Roberto")
                .apellido("Garcia")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .email("beto@gmail.com")
                .password("1234")
                .telefono("123456789")
                .examenes(null)
                .build();
        usuarioRepository.save(usuario);
        Optional<Usuario> usuario1 = usuarioRepository.buscarPorUsernameOEmail("roberto")
                .or(() -> usuarioRepository.buscarPorUsernameOEmail("beto@gmail.com"));
        assertTrue(usuario1.isPresent());
    }

    // Test para buscar un usuario por su rol
    @Test
    void should_FindByRol() {
        Usuario usuario = Usuario.builder()
                .username("roberto")
                .nombre("Roberto")
                .apellido("Garcia")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .email("beto@gmial.com")
                .password("1234")
                .telefono("123456789")
                .examenes(null)
                .build();
        usuarioRepository.save(usuario);
        Optional<Usuario> usuario1 = usuarioRepository.buscarPorRol("ADMIN");
        assertThat(usuario1).isEmpty();
    }

    // Test para buscar un usuario por su examen
    @Test
    void should_FindByExamen() {
        Category categoria = Category.builder()
                .categoriaId(1L)
                .titulo("Lengua")
                .descripcion("Categoria de lengua")
                .build();
        categoriaRepository.save(categoria);

        Usuario usuario = Usuario.builder()
                .usuarioId(1L)
                .username("roberto")
                .nombre("Roberto")
                .apellido("Garcia")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .email("beto@gmail.com")
                .password("1234")
                .telefono("123456789")
                .examenes(null)
                .build();
        usuarioRepository.save(usuario);

        Examen examen = Examen.builder()
                .titulo("Examen de Aleman")
                .descripcion("Examen de gramática nivel básico")
                .puntosMaximos("10")
                .numeroDePreguntas("5")
                .activo(true)
                .nota(5.1)
                .categoria(categoria)
                .usuario(usuario)
                .build();

        examenRepository.save(examen);
        Optional<Usuario> usuario1 = usuarioRepository.buscarPorExamen(examen.getExamenId());
        assertThat(usuario1).isNotEmpty();

        System.out.println("El " + examen.getTitulo() + " pertenece al usuario: " + usuario1.get().getUsername());

    }

    // Test para buscar un usuario por su nota de examen
    @Test
    void should_FindByNotaExamen() {
        Category categoria = Category.builder()
                .categoriaId(1L)
                .titulo("Lengua")
                .descripcion("Categoria de lengua")
                .build();
        categoriaRepository.save(categoria);

        Usuario usuario = Usuario.builder()
                .usuarioId(1L)
                .username("roberto")
                .nombre("Roberto")
                .apellido("Garcia")
                .fechaNacimiento(LocalDate.of(1990, 1, 1))
                .email("beto@gmail.com")
                .password("1234")
                .telefono("123456789")
                .examenes(null)
                .build();
        usuarioRepository.save(usuario);

        Examen examen = Examen.builder()
                .titulo("Examen de Aleman")
                .descripcion("Examen de gramática nivel básico")
                .puntosMaximos("10")
                .numeroDePreguntas("5")
                .activo(true)
                .nota(5.1)
                .categoria(categoria)
                .usuario(usuario)
                .build();
        examenRepository.save(examen);
        Optional<Usuario> usuario1 = usuarioRepository.buscarPorNotaExamen(5.1);
        assertThat(usuario1).isNotEmpty();

        System.out.println("El usuario " + usuario1.get().getUsername() + " tiene una nota de " + examen.getNota() + " en el examen " + examen.getTitulo());
    }

}