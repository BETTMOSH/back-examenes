package es.roberto.gestionexamenesbackend.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.entity.Usuario;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@DBUnit
@Slf4j
class ExamenRepositoryTest {

    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private UsuarioRepository usuarioRepository;



    //test para crear un examen
    @Test
    public void encontrarExamen() {
        Categoria categoria = Categoria.builder()
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
                .email("hola@gmail.com")
                .password("1234")
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
        Examen examenDB = examenRepository.findByTituloContainsIgnoreCase("examen de aleman");
        assertThat(examenDB).isNotNull();
        assertThat(examenDB.getExamenId()).isNotNull();



    }
    // test para buscar una categoria de un examen
    @Test
    public void testFindByCategoria() {
        Categoria categoria = Categoria.builder()
                .categoriaId(1L)
                .titulo("Frances")
                .descripcion("Categoria de frances")
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
                .build();
        usuarioRepository.save(usuario);

        Examen examen = Examen.builder()
                .titulo("Examen de Frances")
                .descripcion("Examen de gramática nivel básico")
                .puntosMaximos("10")
                .numeroDePreguntas("5")
                .activo(true)
                .nota(5.1)
                .categoria(categoria)
                .usuario(usuario)
                .build();
        examenRepository.save(examen);
        List<Examen> examenes = examenRepository.findByCategoria(categoria);
        assertThat(examenes.size()).isEqualTo(1);
        assertThat(examenes.get(0).getCategoria().getTitulo()).isEqualTo("Frances");

        }

    // test para buscar examenes activos
    @Test
    public void testFindByActivo() {
        Categoria categoria = Categoria.builder()
                .categoriaId(1L)
                .titulo("Frances")
                .descripcion("Categoria de frances")
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
                .build();
        usuarioRepository.save(usuario);

        Examen examen = Examen.builder()
                .titulo("Examen de Frances")
                .descripcion("Examen de gramática nivel básico")
                .puntosMaximos("10")
                .numeroDePreguntas("5")
                .activo(true)
                .nota(5.1)
                .categoria(categoria)
                .usuario(usuario)
                .build();
        examenRepository.save(examen);

        List<Examen> examenes = examenRepository.findByActivo(true);
        assertThat(examenes.size()).isEqualTo(1);
        assertThat(examenes.get(0).isActivo()).isTrue();
        System.out.println(examenes.get(0).getTitulo() + " esta activo o " + examenes.get(0).isActivo());

    }
}