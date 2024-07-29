package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.ComparatorCategory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;

@DataJpaTest
@ComponentScan(
        basePackages = "br.com.vicente.multitoolsbackend",
        useDefaultFilters = false,
        includeFilters = {
                @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*DbGateway")
        }
)
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