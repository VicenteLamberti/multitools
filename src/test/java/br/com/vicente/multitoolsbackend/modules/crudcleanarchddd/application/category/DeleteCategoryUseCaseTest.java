package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.DeleteCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.models.DeleteCategoryCommand;
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
class DeleteCategoryUseCaseTest {

    @Mock
    private CategoryGateway categoryGateway;
    @InjectMocks
    private DeleteCategoryUseCase useCase;

    @Test
    @DisplayName("Deve buscar o registro de categoria, e deletar")
    void givenValidCategoryID_whenCallsExecute_shouldDelete() {

        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        final CategoryID categoryID = category.getId();
        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());

        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);

        Assertions.assertFalse(category.isDeleted());
        Assertions.assertNull(category.getDeletedAt());
        //When
        Assertions.assertDoesNotThrow(() -> useCase.execute(cmd));

        //Then
        Assertions.assertTrue(category.isDeleted());
        Assertions.assertNotNull(category.getDeletedAt());
        Mockito.verify(categoryGateway, Mockito.times(1)).delete(category);

    }

    @Test
    @DisplayName("Caso ocorra uma exceção ao buscar a Category, deve ocorrer uma exceção no usecase")
    void givenOccurExceptionAtGetCategory_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given
        final CategoryID categoryID = CategoryID.generate();
        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());

        final String expectedExceptionMessage = "Unable to delete category.";

        Mockito.when(categoryGateway.getByID(categoryID)).thenThrow(new RuntimeException("Any message"));

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(cmd));

        Mockito.verify(categoryGateway,Mockito.times(0)).delete(Mockito.any());

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());

    }

    @Test
    @DisplayName("Caso ocorra uma exceção no método de dominio deletar a Category, deve ocorrer uma exceção no usecase")
    void givenOccurExceptionAtDeleteCategoryDomainMethod_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given

        final Product product = ProductBuilder.builderDummy().rebuild();
        final Category category = CategoryBuilder.builderDummy()
                .withProducts(List.of(product))
                .rebuild();

        final CategoryID categoryID = category.getId();
        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());
        final String expectedExceptionMessage = "Unable to delete category.";

        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(cmd));
        Mockito.verify(categoryGateway,Mockito.times(0)).delete(Mockito.any());

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
    }

    @Test
    @DisplayName("Caso ocorra uma exceção no método de gateway delete a Category, deve ocorrer uma exceção no usecase")
    void givenOccurExceptionAtDeleteCategoryGatewayMethod_whenCallsExecute_shouldThrowsExceptionInUseCase() {
        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        final CategoryID categoryID = category.getId();
        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());
        final String expectedExceptionMessage = "Unable to delete category.";

        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);
        Mockito.doThrow(new RuntimeException("Any error")).when(categoryGateway).delete(category);

        //When
        final NotificationException actualException = Assertions.assertThrows(NotificationException.class, () -> useCase.execute(cmd));

        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
    }
}