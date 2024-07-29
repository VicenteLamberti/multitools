package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure;

import br.com.vicente.multitoolsbackend.shared.domain.utils.ObjectMapperCustom;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

public interface MockDsl {

    MockMvc mvc();




    default ResultActions postRequest(final String url,
                                      final Object body
    ) throws Exception {

        final var request = post(url)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(ObjectMapperCustom.writeValueAsString(body));

        return this.mvc().perform(request).andDo(MockMvcResultHandlers.print());
    }

}