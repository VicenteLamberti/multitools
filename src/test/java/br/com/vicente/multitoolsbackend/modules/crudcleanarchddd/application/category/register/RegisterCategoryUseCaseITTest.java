package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.IntegrationTest;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryJpa;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryRepository;
import br.com.vicente.multitoolsbackend.shared.usecase.exception.UseCaseException;
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
class RegisterCategoryUseCaseITTest {
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

    @Test
    @DisplayName("Caso ocorra uma exceção no método de Register de dominio, deve ser capturado, e lançado no use case com os erros que ocorreram no dominio")
    void givenOccurExceptionInDomainMethod_whenCallsExecute_shouldThrowsExceptionInUseCase(){
        //Given
        final String name = "";
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(name);

        //When
        final UseCaseException actualException = assertThrows(UseCaseException.class, () -> useCase.execute(cmd));

        //Then
        Mockito.verify(categoryGateway,Mockito.never()).register(Mockito.any());
        Assertions.assertNotNull(actualException);
        Assertions.assertFalse(actualException.getErrors().isEmpty());
        Assertions.assertEquals(0, categoryRepository.count());
    }

    @Test
    @DisplayName("Caso ocorra uma exceção no método de Register do gateway, deve ser capturado, e lançado no use case com os erros que ocorreram no gateway")
    void givenOccurExceptionInMethodRegisterOfGateway_whenCallsExecute_shouldThrowsExceptionInUseCase(){
        //Given
        final String expectedExceptionMessage = "Unable to register category";
        final List<String> expectedErrors = List.of("Error in gateway");
        final String name = "Category Name";
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(name);

        Mockito.doThrow(new RuntimeException(expectedErrors.getFirst())).when(categoryGateway).register(Mockito.any());

        //When
        final UseCaseException actualException = assertThrows(UseCaseException.class, () -> useCase.execute(cmd));

        //Then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
        Assertions.assertTrue(actualException.getErrors().containsAll(expectedErrors));
        Assertions.assertEquals(0, categoryRepository.count());
    }

}