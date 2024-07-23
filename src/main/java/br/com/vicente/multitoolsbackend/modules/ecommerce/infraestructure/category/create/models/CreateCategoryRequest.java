package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models;

import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCategoryRequest(
        @JsonProperty(Strings.NAME)
        String name
) {
    public static CreateCategoryRequest with(final String name) {
        return new CreateCategoryRequest(
                name
        );
    }
}
