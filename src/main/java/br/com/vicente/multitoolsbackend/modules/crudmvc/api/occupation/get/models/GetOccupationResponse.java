package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.get.models;

import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence.OccupationJpa;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public record GetOccupationResponse(
        @JsonProperty(Strings.ID)
        Long id,
        @JsonProperty(Strings.NAME)
        String name,
        @JsonProperty(Strings.DELETED)
        boolean deleted,
        @JsonProperty(Strings.DELETED_AT)
        LocalDateTime deletedAt,
        @JsonProperty(Strings.UPDATED_AT)
        LocalDateTime updatedAt
) {
    public static GetOccupationResponse from(final OccupationJpa occupationJpa) {
        return new GetOccupationResponse(
                occupationJpa.getId(),
                occupationJpa.getName(),
                occupationJpa.isDeleted(),
                occupationJpa.getDeletedAt(),
                occupationJpa.getUpdatedAt()
        );
    }
}