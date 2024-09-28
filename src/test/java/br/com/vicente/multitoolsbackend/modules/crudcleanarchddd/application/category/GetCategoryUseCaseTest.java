package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.GetCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.models.GetCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.models.GetCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.shared.usecase.exception.NotificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GetCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;
    @InjectMocks
    private GetCategoryUseCase useCase;


    @Test
    @DisplayName("Should find register of category by id and return output")
    void givenValidID_whenCallsExecute_shouldReturnOutput(){

        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        final CategoryID expectedCategoryID = category.getId();
        final GetCategoryCommand cmd = GetCategoryCommand.with(expectedCategoryID.getValue());

        Mockito.when(categoryGateway.getByID(expectedCategoryID)).thenReturn(category);

        //When
        final GetCategoryOutput actualOutput = useCase.execute(cmd);

        //Then
        //TODO fazer um teste para o from do output e fazer um comparator de output com dominio e garantir que todos campos estao sendo validados
        Assertions.assertNotNull(actualOutput);
        Assertions.assertEquals(expectedCategoryID, actualOutput.id());
    }

    @Test
    @DisplayName("Should throws exception if occur error in gateway get category")
    void givenOccurExceptionAtGetCategory_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given
        final CategoryID categoryID = CategoryID.generate();
        final GetCategoryCommand cmd = GetCategoryCommand.with(categoryID.getValue());
        final String expectedExceptionMessage = "Unable to get category.";
        Mockito.when(categoryGateway.getByID(categoryID)).thenThrow(new RuntimeException("Any message"));

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(cmd));


        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());

    }

}