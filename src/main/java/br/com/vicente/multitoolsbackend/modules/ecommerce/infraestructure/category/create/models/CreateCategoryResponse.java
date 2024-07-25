package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record CreateCategoryResponse(
        @JsonProperty(Strings.ID)
        String id,
        @JsonProperty(Strings.NAME)
        String name
) {
    public static CreateCategoryResponse from(final RegisterCategoryOutput output) {
        return new CreateCategoryResponse(
                output.id().getValue(),
                output.name()
        );
    }
}