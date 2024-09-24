package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.IntegrationTest;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.models.DeleteCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryJpa;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

@IntegrationTest
class DeleteCategoryUseCaseITTest {

    @SpyBean
    private CategoryGateway categoryGateway;
    @Autowired
    private DeleteCategoryUseCase useCase;
    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Deve buscar o registro de categoria, e deletar")
    void givenValidCategoryID_whenCallsExecute_shouldDelete() {
        //Given
        final Category category = CategoryBuilder.builderDummy().rebuild();
        categoryRepository.saveAndFlush(CategoryJpa.from(category));
        final CategoryID categoryID = category.getId();
        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());

        Assertions.assertEquals(1, categoryRepository.count());

        Assertions.assertFalse(category.isDeleted());
        Assertions.assertNull(category.getDeletedAt());
        //When
        Assertions.assertDoesNotThrow(() -> useCase.execute(cmd));

        //Then
        final CategoryJpa categoryJpa = categoryRepository.findById(categoryID.getValue()).orElseThrow();
        Mockito.verify(categoryGateway, Mockito.times(1)).delete(category);
        Assertions.assertEquals(1, categoryRepository.count());
        Assertions.assertTrue(categoryJpa.isDeleted());
        Assertions.assertNotNull(categoryJpa.getDeletedAt());



    }

    //todo parei aqui, preciso alterar o notfound ex eption para nao ter lista de erro
//    @Test
//    @DisplayName("Caso ocorra uma exceção ao buscar a Category, deve ocorrer uma exceção no usecase")
//    void givenOccurExceptionAtGetCategory_whenCallsExecute_shouldThrowsExceptionInUseCase() {
//        //Given
//        final CategoryID categoryID = CategoryID.generate();
//        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());
//
//        final String expectedExceptionMessage = "Unable to delete category";
//
//        Mockito.when(categoryGateway.getByID(categoryID)).thenThrow(new RuntimeException("Any message"));
//
//        //When
//        final UseCaseException actualException = Assertions.assertThrows(UseCaseException.class, () -> useCase.execute(cmd));
//
//        Mockito.verify(categoryGateway,Mockito.times(0)).delete(Mockito.any());
//
//        Assertions.assertNotNull(actualException);
//        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
//        Assertions.assertFalse(actualException.getErrors().isEmpty());
//
//    }

    //todo testes comentados

//    @Test
//    @DisplayName("Caso ocorra uma exceção no método de dominio deletar a Category, deve ocorrer uma exceção no usecase")
//    void givenOccurExceptionAtDeleteCategoryDomainMethod_whenCallsExecute_shouldThrowsExceptionInUseCase() {
//        //Given
//
//        final Product product = ProductBuilder.builderDummy().rebuild();
//        final Category category = CategoryBuilder.builderDummy()
//                .withProducts(List.of(product))
//                .rebuild();
//
//        final CategoryID categoryID = category.getId();
//        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());
//        final String expectedExceptionMessage = "Unable to delete category";
//
//        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);
//
//        //When
//        final UseCaseException actualException = Assertions.assertThrows(UseCaseException.class, () -> useCase.execute(cmd));
//        Mockito.verify(categoryGateway,Mockito.times(0)).delete(Mockito.any());
//
//        Assertions.assertNotNull(actualException);
//        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
//        Assertions.assertFalse(actualException.getErrors().isEmpty());
//    }
//
//    @Test
//    @DisplayName("Caso ocorra uma exceção no método de gateway delete a Category, deve ocorrer uma exceção no usecase")
//    void givenOccurExceptionAtDeleteCategoryGatewayMethod_whenCallsExecute_shouldThrowsExceptionInUseCase() {
//        //Given
//        final Category category = CategoryBuilder.builderDummy().rebuild();
//        final CategoryID categoryID = category.getId();
//        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(categoryID.getValue());
//        final String expectedExceptionMessage = "Unable to delete category";
//
//        Mockito.when(categoryGateway.getByID(categoryID)).thenReturn(category);
//        Mockito.doThrow(new RuntimeException("Any error")).when(categoryGateway).delete(category);
//
//        //When
//        final UseCaseException actualException = Assertions.assertThrows(UseCaseException.class, () -> useCase.execute(cmd));
//
//        Assertions.assertNotNull(actualException);
//        Assertions.assertEquals(expectedExceptionMessage, actualException.getMessage());
//        Assertions.assertFalse(actualException.getErrors().isEmpty());
//    }
}