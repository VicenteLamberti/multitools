package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.DeleteCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.models.DeleteCategoryCommand;
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
class DeleteCategoryUseCaseIT {

    @SpyBean
    private CategoryGateway categoryGateway;
    @Autowired
    private DeleteCategoryUseCase useCase;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("It should fetch the category record and delete it.")
    void givenValidCategoryID_whenCallsExecute_shouldDelete() {
        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        categoryRepository.saveAndFlush(CategoryJpa.from(category));
        final CategoryID categoryID = category.getId();
        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertFalse(category.isDeleted());
        Assertions.assertNull(category.getDeletedAt());
        //When
        Assertions.assertDoesNotThrow(() -> useCase.execute(cmd));

        //Then
        final CategoryJpa categoryJpa = categoryRepository.findById(categoryID.getValue()).orElseThrow();
        Mockito.verify(categoryGateway, Mockito.times(1)).delete(category);
        Assertions.assertEquals(1, categoryRepository.count());
        Assertions.assertTrue(categoryJpa.isDeleted());
        Assertions.assertNotNull(categoryJpa.getDeletedAt());


    }
}