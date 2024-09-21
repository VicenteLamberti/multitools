package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class DeleteCategoryTest {

    @Test
    @DisplayName("Caso não exista produtos vinculados, não deve lançar exceção")
    void givenNoLinkedProducts_whenProcess_thenDoNotThrowException(){
        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();

        Assertions.assertFalse(category.isDeleted());
        Assertions.assertNull(category.getDeletedAt());
        //When
        Assertions.assertDoesNotThrow(()->category.delete());

        //Then
        //TODO colocar clonable para comparar que nao teve alteracao
//        Assertions.assertEquals();
        Assertions.assertTrue(category.isDeleted());
        Assertions.assertNotNull(category.getDeletedAt());
    }

    @Test
    @DisplayName("Caso exista produtos vinculados, deve lançar exceção")
    void givenLinkedProducts_whenProcess_thenDoThrowException(){
        //Given
        final String expectedExceptionMessage = "errorAtDeleteCategory";
        final List<String> expectedErrors = List.of("productsLinked");

        final List<Product> products = List.of(ProductBuilder.builderDummy().rebuild()); // todo ver como não permitir usar o build aqui
        final Category category = CategoryBuilder.builderDummy()
                .withProducts(products)
                .rebuild();

        //When
        final DomainException actualException = Assertions.assertThrows(DomainException.class, () -> category.delete());

        //Then
        Assertions.assertNotNull(actualException);
        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
        Assertions.assertFalse(actualException.getErrors().isEmpty());
        Assertions.assertTrue(actualException.getErrors().containsAll(expectedErrors));
    }

}
