package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.create.models;

import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterCategoryRequest(
        @JsonProperty(Strings.NAME)
        String name
) {
    public static RegisterCategoryRequest with(final String name) {
        return new RegisterCategoryRequest(
                name
        );
    }
}
