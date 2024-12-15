package es.roberto.gestionexamenesbackend.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import es.roberto.gestionexamenesbackend.entity.Categoria;
import es.roberto.gestionexamenesbackend.entity.Examen;
import es.roberto.gestionexamenesbackend.entity.Pregunta;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@DBUnit
@Slf4j
class PreguntaRepositoryTest {

    @Autowired
    private PreguntaRepository preguntaRepository;
    @Autowired
    private ExamenRepository examenRepository;
    @Autowired
    private CategoriaRepository categoriaRepository;


    // Test para buscar una pregunta con su respuesta
    @Test
    void should_FindByRespuestaCorrecta() {
        Categoria categoria = Categoria.builder()
                .categoriaId(1L)
                .titulo("Ingles")
                .descripcion("Categoria de ingles")
                .build();
        categoriaRepository.save(categoria);

        Examen examen = Examen.builder()
                .examenId(1L)
                .titulo("Examen de Aleman")
                .descripcion("Examen de gramática nivel básico")
                .puntosMaximos("10")
                .numeroDePreguntas("5")
                .activo(true)
                .nota(5.1)
                .categoria(categoria)
                .build();
        examenRepository.save(examen);

        Pregunta pregunta = Pregunta.builder()
                .preguntaId(1L)
                .contenido("Enunciado1")
                .opcion1("opcion1")
                .opcion2("opcion2")
                .opcion3("opcion3")
                .opcion4("opcion4")
                .respuestaDada("opcion1")
                .respuesta("opcion1")
                .examen(examen)
                .build();
        preguntaRepository.save(pregunta);
        Pregunta pregunta1 = preguntaRepository.findByRespuesta("opcion1");
        assertThat(pregunta1.getRespuesta()).contains("opcion1");
        System.out.println("La respuesta correcta es: " + pregunta1.getRespuesta());

    }

    // Test query method para buscar una respuesta correcta y el examen al que pertenece
    @Test
    void should_FindByExamenAndRespuesta() {
        Categoria categoria = Categoria.builder()
                .categoriaId(1L)
                .titulo("Ingles")
                .descripcion("Categoria de ingles")
                .build();
        categoriaRepository.save(categoria);

        Examen examen = Examen.builder()
                .examenId(1L)
                .titulo("Examen de Aleman")
                .descripcion("Examen de gramática nivel básico")
                .puntosMaximos("10")
                .numeroDePreguntas("5")
                .activo(true)
                .nota(5.1)
                .categoria(categoria)
                .build();
        examenRepository.save(examen);

        Pregunta pregunta = Pregunta.builder()
                .preguntaId(1L)
                .contenido("Enunciado1")
                .opcion1("opcion1")
                .opcion2("opcion2")
                .opcion3("opcion3")
                .opcion4("opcion4")
                .respuestaDada("opcion1")
                .respuesta("opcion1")
                .examen(examen)
                .build();
        preguntaRepository.save(pregunta);
        Set<Pregunta> pregunta1 = preguntaRepository.findByExamenAndRespuesta(examen, "opcion1");
        assertThat(pregunta1).isNotNull();
        System.out.println("la respuesta correcta es: " + pregunta.getRespuesta() + " y pertenece al " + examen.getTitulo());
    }


}