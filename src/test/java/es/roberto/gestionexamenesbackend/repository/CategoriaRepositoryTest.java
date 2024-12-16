package es.roberto.gestionexamenesbackend.repository;

import com.github.database.rider.core.api.configuration.DBUnit;
import com.github.database.rider.core.api.dataset.DataSet;

import es.roberto.gestionexamenesbackend.entity.Category;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;


@DataJpaTest
@DBUnit
class CategoriaRepositoryTest {

    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private ExamenRepository examenRepository;

    // Test para buscar una categoria por su titulo
    @Test
    @DataSet("datasets/categorias.yml")
    void should_FindByTitulo() {
        Category cat = Category.builder()
                .titulo("Categoria1")
                .descripcion("Descripcion1").build();
        categoriaRepository.save(cat);
        Category categoria = categoriaRepository.findByTituloContainsIgnoreCase("ego");
        assertThat(categoria.getTitulo()).contains("ego");
    }

    // Test para crear una categoria
    @Test
    @DataSet(value= {"datasets/categorias.yml"})
    void should_CreateCategoria() {
        Category cat = Category.builder()
                .titulo("Categoria1")
                .descripcion("Descripcion1").build();
        categoriaRepository.save(cat);
        Category categoria = categoriaRepository.findByTituloContainsIgnoreCase("cateGoria1");
        assertThat(categoria.getTitulo()).contains("Categoria1");
    }

    // Test para eliminar una categoria
    @Test
    @DataSet(value= {"datasets/categorias.yml"})
    void should_DeleteCategoria() {
        Category cat = Category.builder()
                .titulo("Categoria1")
                .descripcion("Descripcion1").build();
        categoriaRepository.save(cat);

        Category categoria = categoriaRepository.findByTituloContainsIgnoreCase("cateGoria1");
        assertThat(categoria.getTitulo()).contains("Categoria1");
        // Eliminamos la categoria creada
        categoriaRepository.deleteById(categoria.getCategoriaId());
        Category categoria2 = categoriaRepository.findByTituloContainsIgnoreCase("cateGoria1");
        assertThat(categoria2).isNull();
    }


}