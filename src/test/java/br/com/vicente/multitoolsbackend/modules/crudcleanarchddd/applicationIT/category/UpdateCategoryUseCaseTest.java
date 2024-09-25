package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.UpdateCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.models.UpdateCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.IntegrationTest;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryJpa;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

@IntegrationTest
class UpdateCategoryUseCaseTest {

    @SpyBean
    private CategoryGateway categoryGateway;
    @Autowired
    private UpdateCategoryUseCase useCase;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("It should fetch the category record and update it.")
    void givenValidCategoryID_whenCallsExecute_shouldUpdate() {

        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        categoryRepository.saveAndFlush(CategoryJpa.from(category));
        final CategoryID categoryID = category.getId();
        final String expectedName = "Vicente";
        final UpdateCategoryCommand cmd = UpdateCategoryCommand.with(categoryID.getValue(), expectedName);


        Assertions.assertNull(category.getUpdatedAt());
        Assertions.assertNotEquals(expectedName, category.getName());

        //When
        Assertions.assertDoesNotThrow(() -> useCase.execute(cmd));

        //Then
        final CategoryJpa categoryJpa = categoryRepository.findById(categoryID.getValue()).orElseThrow();
        Assertions.assertNotNull(categoryJpa.getUpdatedAt());
        Assertions.assertEquals(expectedName, categoryJpa.getName());
        Mockito.verify(categoryGateway, Mockito.times(1)).update(category);

    }

    parei aqui falta os tes de use case e it de list e get
}