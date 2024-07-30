package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.E2ETest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.junit.jupiter.Testcontainers;

import static br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.ContainerBaseTestExtension.POSTGRE_SQL_CONTAINER;

@E2ETest
@Testcontainers
class CategoryE2ERegisterTest implements MockDslCategory {

    @Autowired
    private MockMvc mockMvc;

    @Override
    public MockMvc mvc() {
        return mockMvc;
    }

    @DynamicPropertySource
    public static void setDatasourceProperties(final DynamicPropertyRegistry registry) {
        Integer mappedPort = POSTGRE_SQL_CONTAINER.getMappedPort(5432);
        System.out.printf("Container is running on port %s\n", mappedPort);
        registry.add("postgres.port", () -> mappedPort);
    }

    @MockBean
    private RegisterCategoryUseCase registerCategoryUseCase;


    @Test
    @DisplayName("Deve retonar retornar o response a partir do output")
    void givenValidCategoryName_whenRegisterCategory_thenReturnCreatedStatusAndCorrectResponse() throws Exception {
        //Given

        final String expectedName = "Category name";
        final CategoryID expectedID = CategoryID.generate();
        final String expectedHeader = "/categories/" + expectedID.getValue();

        //When
        final ResultActions actualResult = register(expectedName);

        //Then
        actualResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", expectedHeader))
                .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.equalTo(expectedID.getValue())))
                .andExpect(MockMvcResultMatchers.jsonPath("name", Matchers.equalTo(expectedName)));

    }


}