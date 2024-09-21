package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterCategoryResponse(
        @JsonProperty(Strings.ID)
        String id
) {
    public static RegisterCategoryResponse from(final RegisterCategoryOutput output) {
        return new RegisterCategoryResponse(
                output.id().getValue()
        );
    }
}