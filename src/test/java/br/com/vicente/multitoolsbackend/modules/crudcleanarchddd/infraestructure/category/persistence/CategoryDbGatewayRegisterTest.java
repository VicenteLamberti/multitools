package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.ComparatorCategory;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.GatewayTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@GatewayTest
class CategoryDbGatewayRegisterTest {

    @Autowired
    private CategoryDbGateway categoryDbGateway;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @DisplayName("Deve transformar o objeto Category em CategoryJpa e persistir")
    void givenValidCategory_whenCallsRegister_shouldPersistIt() {
        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        Assertions.assertEquals(0, categoryRepository.count());

        //When
        categoryDbGateway.register(category);

        //Then
        Assertions.assertEquals(1, categoryRepository.count());

        final CategoryJpa expectedCategoryJpa = categoryRepository.findAll().getFirst();
        ComparatorCategory.compare(expectedCategoryJpa, category);

    }

}