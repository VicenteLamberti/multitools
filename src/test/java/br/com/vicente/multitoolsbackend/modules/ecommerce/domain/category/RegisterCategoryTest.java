package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RegisterCategoryTest {

    @Test
    @DisplayName("Deve construir um novo objeto Category, valores que devem ser default, não devem ser alterados mesmo se for passado parâmetros")
    void givenValidParams_whenCallsRegister_shouldReturnNewCategory() {
        //Given
        final String expectedName = "John";

        //When
        final Category actualCategory = Category.register(expectedName);

        //Then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(expectedName, actualCategory.getName());
    }
}