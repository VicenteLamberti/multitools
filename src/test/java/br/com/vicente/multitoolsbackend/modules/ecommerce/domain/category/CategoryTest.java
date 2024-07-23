package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CategoryTest {

    @Test
    @DisplayName("Deve criar um novo objeto Category, os valores que não forem passados devem ser criados com os valores default")
    void givenValidParams_whenBuilderCategory_shouldInstantiateObjectOfCategory() {
        //Given
        final String expectedName = "John";

        //When
        final Category actualCategory = CategoryBuilder.builder()
                .withName(expectedName)
                .build();

        //Then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertTrue(actualCategory.getProducts().isEmpty());
        Assertions.assertFalse(actualCategory.isDeleted());
    }


    parei em fazer o dummy de produto para fazer
    para fazer o teste passando os outros atributos
}