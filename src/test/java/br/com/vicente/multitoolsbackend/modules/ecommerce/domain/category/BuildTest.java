package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductDummyBuilder;
import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class BuildTest {

    @Test
    @DisplayName("Deve construir um novo objeto Category, valores que devem ser default, não devem ser alterados mesmo se for passado parâmetros")
    void givenValidParams_whenCallsBuild_shouldInstantiateNewObjectWithDefaultValues() {
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

    @ParameterizedTest
    @MethodSource
    @DisplayName("Caso algum parâmetro seja inválido deve ocorrer uma exceção")
    void givenInvalidParams_whenCallsBuild_shouldThrowsException(
            final String name,
            final boolean deleted,
            final List<String> expectedErrors
    ) {
        //Given

        //When
        DomainException exception = Assertions.assertThrows(DomainException.class, () -> CategoryBuilder.builder()
                .withName(name)
                .withDeleted(deleted)
                .build());

        //Then
        Assertions.assertNotNull(exception);
        Assertions.assertTrue(exception.getErrors().containsAll(expectedErrors));

    }

    private static Stream<Arguments> givenInvalidParams_whenCallsBuild_shouldThrowsException() {
        // Given
        return Stream.of(
                Arguments.of(null, true, List.of("nameShouldNotBeEmpty", "nameShouldNotBeBlank")),
                Arguments.of("", true, List.of("nameShouldNotBeEmpty", "nameShouldNotBeBlank")),
                Arguments.of("INVALIDIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZE", true, List.of("nameShouldNotBeMoreThan255Character"))


        );
    }
}