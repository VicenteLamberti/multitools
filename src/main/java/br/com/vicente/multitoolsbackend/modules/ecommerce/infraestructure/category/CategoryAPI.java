package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.RegisterCategoryRequest;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.RegisterCategoryResponse;
import br.com.vicente.multitoolsbackend.shared.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(Strings.URL_CATEGORIES)
public interface CategoryAPI {

    @PostMapping
    ResponseEntity<RegisterCategoryResponse> create(final @RequestBody RegisterCategoryRequest request);
}
