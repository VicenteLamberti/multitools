package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.DeleteCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.GetCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.ListCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.UpdateCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.ControllerTest;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ControllerTest(controllers = CategoryAPI.class)
class CategoryControllerRegisterTest implements MockDslCategory {
    @Autowired
    private MockMvc mockMvc;

    @Override
    public MockMvc mvc() {
        return mockMvc;
    }

    @MockBean
    private RegisterCategoryUseCase registerCategoryUseCase;
    @MockBean
    private DeleteCategoryUseCase deleteCategoryUseCase;
    @MockBean
    private ListCategoryUseCase listCategoryUseCase;
    @MockBean
    private GetCategoryUseCase getCategoryUseCase;
    @MockBean
    private UpdateCategoryUseCase updateCategoryUseCase;



    @Test
    @DisplayName("Deve retonar retornar o response a partir do output")
    void givenValidCategoryName_whenRegisterCategory_thenReturnCreatedStatusAndCorrectResponse() throws Exception {
        //Given

        final String expectedName = "Category name";
        final CategoryID expectedID = CategoryID.generate();
        final String expectedHeader = "/categories/" + expectedID.getValue();

        Mockito.when(registerCategoryUseCase.execute(Mockito.any())).thenReturn(new RegisterCategoryOutput(expectedID));

        //When
        final ResultActions actualResult = register(expectedName);

        //Then
        actualResult.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", expectedHeader))
                .andExpect(MockMvcResultMatchers.jsonPath("id", Matchers.equalTo(expectedID.getValue())));

    }


}