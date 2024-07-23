package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.CreateCategoryRequest;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.CreateCategoryResponse;
import br.com.vicente.multitoolsbackend.shared.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(Strings.URL_CATERGORIES)
public interface CategoryAPI {

    @PostMapping
    ResponseEntity<CreateCategoryResponse> create(final CreateCategoryRequest request);
}
