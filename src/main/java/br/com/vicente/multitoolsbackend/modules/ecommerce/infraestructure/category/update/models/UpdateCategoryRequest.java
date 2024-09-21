package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.update.models;


import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateCategoryRequest(
        @JsonProperty(Strings.NAME)
        String name
) {
}
