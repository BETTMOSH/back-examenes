package es.roberto.gestionexamenesbackend.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;
import es.roberto.gestionexamenesbackend.entity.Categoria;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@DataJpaTest
@DBUnit(schema = "categoria")
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Test
    void testFindAll() {
        List<Categoria> categorias = categoriaRepository.findAll();
        assertThat(categorias.size()).isEqualTo(3);
    }

    @Test
    @DataSet("datasets/categorias.yml")
    void should_FindByIdTitulo() {
        Categoria cat = Categoria.builder()
                .titulo("Categoria1")
                .descripcion("Descripcion1").build();
        categoriaRepository.save(cat);
        Categoria categoria = categoriaRepository.findByTituloContainsIgnoreCase("ego");
        assertThat(categoria.getTitulo()).isEqualTo("Categoria1");

    }

}