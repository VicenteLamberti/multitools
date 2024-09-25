package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.usecase.exception.NotificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegisterCategoryUseCaseTest {
    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private RegisterCategoryUseCase useCase;

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
    }

    @Test
    @DisplayName("Caso ocorra uma exceção no método de Register de dominio, deve ser capturado, e lançado no use case com os erros que ocorreram no dominio")
    void givenOccurExceptionInDomainMethod_whenCallsExecute_shouldThrowsExceptionInUseCase(){
        //Given
        final String name = "";
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(name);

        //When
        final NotificationException actualException = assertThrows(NotificationException.class, () -> useCase.execute(cmd));

        //Then
        Mockito.verify(categoryGateway,Mockito.never()).register(Mockito.any());

        Assertions.assertNotNull(actualException);
        Assertions.assertFalse(actualException.getErrors().isEmpty());
    }

    @Test
    @DisplayName("Caso ocorra uma exceção no método de Register do gateway, deve ser capturado, e lançado no use case com os erros que ocorreram no gateway")
    void givenOccurExceptionInMethodRegisterOfGateway_whenCallsExecute_shouldThrowsExceptionInUseCase(){
        //Given
        final String expectedExceptionMessage = "Unable to register category.";
        final List<String> expectedErrors = List.of("Error in gateway");
        final String name = "Category Name";
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(name);

        Mockito.doThrow(new RuntimeException(expectedErrors.getFirst())).when(categoryGateway).register(Mockito.any());

        //When
        final NotificationException actualException = assertThrows(NotificationException.class, () -> useCase.execute(cmd));

        //Then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
        Assertions.assertTrue(actualException.getErrors().containsAll(expectedErrors));
    }

}