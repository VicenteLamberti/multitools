package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.IntegrationTest;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryJpa;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryRepository;
import br.com.vicente.multitoolsbackend.shared.usecase.exception.NotificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

@IntegrationTest
class RegisterCategoryUseCaseIT {
    @SpyBean
    private CategoryGateway categoryGateway;

    @Autowired
    private RegisterCategoryUseCase useCase;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Deve retornar o output do objeto Category persistido")
    void givenValidCommand_whenCallsExecute_shouldReturnOutputOfCategoryThatWasPassedToGateway(){
        //Given
        final String expectedName = "Category Name";
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(expectedName);

        //When
        final RegisterCategoryOutput actualOutput = useCase.execute(cmd);

        //Then
        final ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        Mockito.verify(categoryGateway, Mockito.times(1)).register(captor.capture());
        final Category categoryWasPassedToGateway = captor.getValue();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());

        Assertions.assertEquals(actualOutput.id(), categoryWasPassedToGateway.getId());

        final CategoryJpa categoryJpa = categoryRepository.findAll().getFirst();

        Assertions.assertNotNull(categoryJpa);
        Assertions.assertEquals(actualOutput.id().getValue(), categoryJpa.getId());
        Assertions.assertEquals(expectedName, categoryJpa.getName());

        //TODO colocar specification
//        Assertions.assertTrue(categoryJpa.getProducts().isEmpty());



    }
}