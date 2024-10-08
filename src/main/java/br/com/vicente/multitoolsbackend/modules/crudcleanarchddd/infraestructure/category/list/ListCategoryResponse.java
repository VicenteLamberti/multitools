package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.list;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.models.ListCategoryOutput;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ListCategoryResponse(
        @JsonProperty(Strings.ID)
        String id,
        @JsonProperty(Strings.NAME)
        String name
) {
    public static ListCategoryResponse from(final ListCategoryOutput output) {
        return new ListCategoryResponse(
                output.id().getValue(),
                output.name()
        );
    }
}