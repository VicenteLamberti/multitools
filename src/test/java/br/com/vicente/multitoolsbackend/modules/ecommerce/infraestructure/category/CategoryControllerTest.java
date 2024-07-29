package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.ControllerTest;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.RegisterCategoryRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

@ControllerTest(controllers = CategoryAPI.class)
class CategoryControllerTest implements MockDslCategory{
    @Autowired
    private MockMvc mockMvc;
    @Override
    public MockMvc mvc() {
        return mockMvc;
    }

    @MockBean
    private RegisterCategoryUseCase registerCategoryUseCase;


    @Test
    void teste() throws Exception {
        //Given

        String name = "Category name";

        Mockito.when(registerCategoryUseCase.execute(Mockito.any())).thenReturn(new RegisterCategoryOutput(CategoryID.generate(),"a"));

        register()

    }


}