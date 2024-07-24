package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

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
        final String expectedName = "";
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(expectedName);

        //When
        final RegisterCategoryOutput actualOutput = useCase.execute(cmd);

        //Then
        final ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        Mockito.verify(categoryGateway, Mockito.times(1)).create(captor.capture());
        final Category categoryWasPassedToGateway = captor.getValue();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());
        Assertions.assertEquals(expectedName, actualOutput.name());

        Assertions.assertEquals(actualOutput.id(), categoryWasPassedToGateway.getId());
        Assertions.assertEquals(actualOutput.name(), categoryWasPassedToGateway.getName());
    }

    @Test
    @DisplayName("Caso o seja enviado um parâmetro inválido, deve ocorrer uma exceção no use case.")
    void givenInvalidCommand_whenCallsExecute_shouldThrowsException(){
        //Given
        final String expectedName = "";
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(expectedName);

        //When
        final RegisterCategoryOutput actualOutput = useCase.execute(cmd);

        //Then
        final ArgumentCaptor<Category> captor = ArgumentCaptor.forClass(Category.class);
        Mockito.verify(categoryGateway, Mockito.times(1)).create(captor.capture());
        final Category categoryWasPassedToGateway = captor.getValue();

        Assertions.assertNotNull(actualOutput);
        Assertions.assertNotNull(actualOutput.id());
        Assertions.assertEquals(expectedName, actualOutput.name());

        Assertions.assertEquals(actualOutput.id(), categoryWasPassedToGateway.getId());
        Assertions.assertEquals(actualOutput.name(), categoryWasPassedToGateway.getName());
    }

}