package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductDummyBuilder;
import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class RegisterTest {

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