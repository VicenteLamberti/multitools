package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.GetCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.models.GetCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.models.GetCategoryOutput;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

@IntegrationTest
class GetCategoryUseCaseIT {

    @SpyBean
    private CategoryGateway categoryGateway;
    @Autowired
    private GetCategoryUseCase useCase;
    @Autowired
    private CategoryRepository categoryRepository;


    @Test
    @DisplayName("Should find register of category by id and return output")
    void givenValidID_whenCallsExecute_shouldReturnOutput() {

        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        categoryRepository.saveAndFlush(CategoryJpa.from(category));
        final CategoryID expectedCategoryID = category.getId();
        final GetCategoryCommand cmd = GetCategoryCommand.with(expectedCategoryID.getValue());


        //When
        final GetCategoryOutput actualOutput = useCase.execute(cmd);

        //Then
        //TODO fazer um teste para o from do output e fazer um comparator de output com dominio e garantir que todos campos estao sendo validados
        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedCategoryID, actualOutput.id());
    }
}