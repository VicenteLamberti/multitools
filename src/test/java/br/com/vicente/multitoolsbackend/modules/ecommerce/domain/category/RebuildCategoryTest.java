package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.ProductDummyBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class RebuildCategoryTest {

    @ParameterizedTest
    @MethodSource
    @DisplayName("Deve criar um novo objeto Category com o construtor de restauração de objeto,devem ser passados todos parâmetros obrigatórios, pois não existe default")
    void givenValidParams_whenCallsRebuild_shouldRebuildObjectOfCategory(
            final boolean expectedDeleted,
            final List<Product> expectedProducts,
            final boolean expectedProductIsEmpty

    ) {
        //Given
        final CategoryID expectedID = CategoryID.generate();
        final String expectedName = "John";

        //When
        final Category actualCategory = CategoryBuilder.builder()
                .withId(expectedID)
                .withName(expectedName)
                .withDeleted(expectedDeleted)
                .withProducts(expectedProducts)
                .rebuild();

        //Then
        Assertions.assertNotNull(actualCategory);
        Assertions.assertEquals(expectedID, actualCategory.getId());
        Assertions.assertEquals(expectedName, actualCategory.getName());
        Assertions.assertTrue(actualCategory.getProducts().containsAll(expectedProducts));
        Assertions.assertEquals(expectedProductIsEmpty, actualCategory.getProducts().isEmpty());
        Assertions.assertEquals(expectedDeleted, actualCategory.isDeleted());
    }

    private static Stream<Arguments> givenValidParams_whenCallsRebuild_shouldRebuildObjectOfCategory() {
        // Given
        return Stream.of(
                Arguments.of(true, List.of(new ProductDummyBuilder(ProductBuilder.builder()).builder().build()), false),
                Arguments.of(false, List.of(new ProductDummyBuilder(ProductBuilder.builder()).builder().build()), false),
                Arguments.of(true, List.of(), true)

        );
    }
}