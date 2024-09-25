package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class UpdateCategoryTest {

    @Test
    @DisplayName("Deve atualizar os atributos de categoria.")
    void givenNoLinkedProducts_whenProcess_thenDeleteCategory(){
        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        final String initialName = category.getName();
        final String expectedName = "Vicente";

        Assertions.assertNull(category.getUpdatedAt());
        //When
        Assertions.assertDoesNotThrow(()->category.update(expectedName));

        //Then
        Assertions.assertNotEquals(initialName, category.getName());
        Assertions.assertEquals(expectedName, category.getName());
    }

    @Test
    @DisplayName("Caso algum dos atributos seja inválido deve ocorrer exceção")
    void givenLinkedProducts_whenProcess_thenDoThrowException(){
        //Given
        final String expectedExceptionMessage = "An error occurred while instantiate category.";
        final List<String> expectedErrors = List.of(
                "Name should not be empty.",
                "Name should not be blank."
        );

        final Category category = CategoryBuilder.builderDummy().rebuild();

        //When
        final DomainException actualException = Assertions.assertThrows(DomainException.class, () -> category.update(null));

        //Then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
        Assertions.assertTrue(actualException.getErrors().containsAll(expectedErrors));
    }

}
