package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.update.models;


import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCategoryRequest(
        @JsonProperty(Strings.NAME)
        String name
) {
}
