package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.get.models;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.get.models.GetCategoryOutput;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record GetCategoryResponse(
        @JsonProperty(Strings.ID)
        String id,
        @JsonProperty(Strings.NAME)
        String name,
        @JsonProperty(Strings.DELETED)
        boolean deleted,
        @JsonProperty(Strings.DELETED_AT)
        LocalDateTime deletedAt,
        @JsonProperty(Strings.UPDATED_AT)
        LocalDateTime updatedAt
) {
    public static GetCategoryResponse from(final GetCategoryOutput output) {
        return new GetCategoryResponse(
                output.id().getValue(),
                output.name(),
                output.deleted(),
                output.deletedAt(),
                output.updatedAt()
        );
    }
}