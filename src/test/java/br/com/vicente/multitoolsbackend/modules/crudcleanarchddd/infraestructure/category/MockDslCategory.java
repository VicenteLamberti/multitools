package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.MockDsl;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.create.models.RegisterCategoryRequest;
import br.com.vicente.multitoolsbackend.shared.Strings;
import org.springframework.test.web.servlet.ResultActions;

public interface MockDslCategory extends MockDsl {

    default ResultActions register(final String name) throws Exception {
        final RegisterCategoryRequest body = RegisterCategoryRequest.with(name);

        return postRequest(Strings.URL_CATEGORIES, body);
    }
}