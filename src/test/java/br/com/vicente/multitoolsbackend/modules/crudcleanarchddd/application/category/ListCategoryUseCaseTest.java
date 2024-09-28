package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.ListCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.models.ListCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.usecase.exception.NotificationException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class ListCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private ListCategoryUseCase useCase;

    @Test
    @DisplayName("Should return list of output")
    void givenValidPersisted_whenCallsExecute_shouldReturnListOutput() {
        //Given
        final Category category1 = CategoryBuilder.builderDummy().rebuild();
        final Category category2 = CategoryBuilder.builderDummy().rebuild();
        final List<String> expectedIDs = List.of(category1.getId().getValue(), category2.getId().getValue());

        Mockito.when(categoryGateway.list()).thenReturn(List.of(category1, category2));

        //When
        final List<ListCategoryOutput> actualOutput = useCase.execute();

        //Then
        //TODO fazer um teste para o from do output e fazer um comparator de output com dominio e garantir que todos campos estao sendo validados
        Assertions.assertNotNull(actualOutput);
        Assertions.assertFalse(actualOutput.isEmpty());
        Assertions.assertTrue(actualOutput.stream().map(output -> output.id().getValue()).toList().containsAll(expectedIDs));
    }

    @Test
    @DisplayName("Should return empty list")
    void givenNotPersisted_whenCallsExecute_shouldReturnEmpty() {
        //Given

        Mockito.when(categoryGateway.list()).thenReturn(List.of());

        //When
        final List<ListCategoryOutput> actualOutput = useCase.execute();

        //Then
        //TODO fazer um teste para o from do output e fazer um comparator de output com dominio e garantir que todos campos estao sendo validados
        Assertions.assertNotNull(actualOutput);
        Assertions.assertTrue(actualOutput.isEmpty());
    }

    @Test
    @DisplayName("Should throws exception if occur error in gateway get category.")
    void givenOccurExceptionAtListCategory_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given
        final String expectedExceptionMessage = "Unable to list category.";
        Mockito.when(categoryGateway.list()).thenThrow(new RuntimeException("Any message"));

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute());


        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());

    }

}