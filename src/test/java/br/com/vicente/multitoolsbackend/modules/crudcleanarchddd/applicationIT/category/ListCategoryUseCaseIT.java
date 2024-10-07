package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.ListCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.models.ListCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.applicationIT.IntegrationTest;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryJpa;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryRepository;
import br.com.vicente.multitoolsbackend.shared.usecase.exception.NotificationException;
import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.SpyBean;

import java.util.List;

@IntegrationTest
class ListCategoryUseCaseIT {

    @SpyBean
    private CategoryGateway categoryGateway;

    @Autowired
    private ListCategoryUseCase useCase;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    @DisplayName("Should return list of output")
    void givenValidPersisted_whenCallsExecute_shouldReturnListOutput() {
        //Given
        System.out.println(categoryRepository.count());
        final Category category1 = CategoryBuilder.builderDummy().rebuild();
        final Category category2 = CategoryBuilder.builderDummy().rebuild();
        categoryRepository.saveAllAndFlush(List.of(CategoryJpa.from(category1), CategoryJpa.from(category2)));
        System.out.println(categoryRepository.count());
        final List<String> expectedIDs = List.of(category1.getId().getValue(), category2.getId().getValue());

        //When
        final List<ListCategoryOutput> actualOutput = useCase.execute();

        //Then
        //TODO fazer um teste para o from do output e fazer um comparator de output com dominio e garantir que todos campos estao sendo validados
        Assertions.assertNotNull(actualOutput);
        Assertions.assertFalse(actualOutput.isEmpty());
        Assertions.assertTrue(actualOutput.stream().map(output -> output.id().getValue()).toList().containsAll(expectedIDs));
    }

    @Test
    @DisplayName("Should return empty list")
    void givenNotPersisted_whenCallsExecute_shouldReturnEmpty() {
        //Given
        System.out.println(categoryRepository.count());
        //When
        final List<ListCategoryOutput> actualOutput = useCase.execute();

        //Then
        //TODO fazer um teste para o from do output e fazer um comparator de output com dominio e garantir que todos campos estao sendo validados
        Assertions.assertNotNull(actualOutput);
        Assertions.assertTrue(actualOutput.isEmpty());
    }
}
