package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Stream;

class BuildCategoryTest {

    @Test
    @DisplayName("Deve construir um novo objeto Category, valores que devem ser default, não devem ser alterados mesmo se for passado parâmetros")
    void givenValidParams_whenCallsBuild_shouldInstantiateNewObjectWithDefaultValues() {
        //Given
        final String expectedName = "John";
        final CategoryID wrongID = CategoryID.from("1");
        final List<Product> wrongProducts = List.of(ProductBuilder.builderDummy().rebuild());
        final LocalDateTime wrongNow = LocalDateTime.now();

        //When
        final Category actualCategory = CategoryBuilder.builder()
                .withName(expectedName)
                .withDeleted(true)
                .withId(wrongID)
                .withProducts(wrongProducts)
                .withDeletedAt(wrongNow)
                .withUpdatedAt(wrongNow)
                .build();

        //Then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(actualCategory.getId());
        Assertions.assertNotEquals(wrongID, actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertTrue(actualCategory.getProducts().isEmpty());
        Assertions.assertFalse(actualCategory.isDeleted());
        Assertions.assertNull(actualCategory.getUpdatedAt());
        Assertions.assertNull(actualCategory.getDeletedAt());
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
        return Stream.of(
                Arguments.of(null, true, List.of("Name should not be empty.", "Name should not be blank.")),
                Arguments.of("", true, List.of("Name should not be empty.", "Name should not be blank.")),
                Arguments.of("INVALIDIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZEINVALIDSIZE", true, List.of("Name should not be greater than 255 characters."))


        );
    }
}