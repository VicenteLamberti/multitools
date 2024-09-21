package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.list.list;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.get.GetCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.list.ListCategoryOutput;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record GetCategoryResponse(
        @JsonProperty(Strings.ID)
        String id,
        @JsonProperty(Strings.NAME)
        String name
) {
    public static GetCategoryResponse from(final GetCategoryOutput output) {
        return new GetCategoryResponse(
                output.id().getValue(),
                output.name()
        );
    }
}