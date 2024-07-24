package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductDummyBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class CategoryTest {

    @Test
    @DisplayName("Deve criar um novo objeto Category com o construtor de criação de objeto, valores que devem ser default, não devem ser alterados mesmo se for passado parâmetros")
    void givenValidParamsWithDefaultValues_whenBuilderCategory_shouldInstantiateObjectOfCategory() {
        //Given
        final String expectedName = "John";
        final CategoryID wrongID = CategoryID.from("1");

        //When
        final Category actualCategory = CategoryBuilder.builder()
                .withName(expectedName)
                .withDeleted(true)
                .withId(wrongID)
                .withProducts(List.of(new ProductDummyBuilder(ProductBuilder.builder()).builder().build()))
                .build();

        //Then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertNotEquals(wrongID, actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertTrue(actualCategory.getProducts().isEmpty());
        Assertions.assertFalse(actualCategory.isDeleted());
    }

    @Test
    @DisplayName("Deve criar um novo objeto Category com o construtor de restauração de objeto,devem ser passados todos parâmetros")
    void givenValidParams_whenBuilderCategory_shouldInstantiateObjectOfCategory() {
        //Given
        final CategoryID expectedID = CategoryID.generate();
        final String expectedName = "John";
        final List<Product> expectedProducts = List.of(new ProductDummyBuilder(ProductBuilder.builder()).builder().build());
        final boolean expectedDeleted = true;
        //When
        final Category actualCategory = CategoryBuilder.builder()
                .withId(expectedID)
                .withName(expectedName)
                .withDeleted(expectedDeleted)
                .withProducts(expectedProducts)
                .generateFrom();

        //Then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(expectedID, actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertTrue(actualCategory.getProducts().containsAll(expectedProducts));
        Assertions.assertEquals(expectedDeleted, actualCategory.isDeleted());
    }
}