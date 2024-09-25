package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.DeleteCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.models.DeleteCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.UpdateCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.models.UpdateCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.ProductBuilder;
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
class UpdateCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;
    @InjectMocks
    private UpdateCategoryUseCase useCase;

    @Test
    @DisplayName("Deve buscar o registro de categoria, e atualizar")
    void givenValidCategoryID_whenCallsExecute_shouldUpdate() {

        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        final CategoryID categoryID = category.getId();
        final String expectedName = "Vicente";
        final UpdateCategoryCommand cmd = UpdateCategoryCommand.with(categoryID.getValue(),expectedName);

        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);

        Assertions.assertNull(category.getUpdatedAt());
        Assertions.assertNotEquals(expectedName, category.getName());

        //When
        Assertions.assertDoesNotThrow(() -> useCase.execute(cmd));

        //Then
        Assertions.assertNotNull(category.getUpdatedAt());
        Assertions.assertEquals(expectedName, category.getName());
        Mockito.verify(categoryGateway, Mockito.times(1)).update(category);

    }

    @Test
    @DisplayName("Caso ocorra uma exceção ao buscar a Category, deve ocorrer uma exceção no usecase")
    void givenOccurExceptionAtGetCategory_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given
        final CategoryID categoryID = CategoryID.generate();
        final UpdateCategoryCommand cmd = UpdateCategoryCommand.with(categoryID.getValue(),"Any name");

        final String expectedExceptionMessage = "Unable to update category.";

        Mockito.when(categoryGateway.getByID(categoryID)).thenThrow(new RuntimeException("Any message"));

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(cmd));

        Mockito.verify(categoryGateway,Mockito.times(0)).delete(Mockito.any());

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());

    }

    @Test
    @DisplayName("Caso ocorra uma exceção no método de dominio atualizar a Category, deve ocorrer uma exceção no usecase")
    void givenOccurExceptionAtDeleteCategoryDomainMethod_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given

        final Category category = CategoryBuilder.builderDummy().rebuild();

        final CategoryID categoryID = category.getId();
        final UpdateCategoryCommand cmd = UpdateCategoryCommand.with(categoryID.getValue(),null);
        final String expectedExceptionMessage = "Unable to update category.";

        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(cmd));
        Mockito.verify(categoryGateway,Mockito.times(0)).update(Mockito.any());

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
    }

    @Test
    @DisplayName("If an exception occurs in the gateway method to update a Category, an exception should occur in the use case.")
    void givenOccurExceptionAtUpdateCategoryGatewayMethod_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        final CategoryID categoryID = category.getId();
        final UpdateCategoryCommand cmd = UpdateCategoryCommand.with(categoryID.getValue(),"Any name");
        final String expectedExceptionMessage = "Unable to delete category.";

        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);
        Mockito.doThrow(new RuntimeException("Any error")).when(categoryGateway).update(category);

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(cmd));

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
    }
}