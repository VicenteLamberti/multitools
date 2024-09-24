package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.list.models;

import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence.OccupationJpa;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record ListOccupationResponse(
        @JsonProperty(Strings.ID)
        Long id,
        @JsonProperty(Strings.NAME)
        String name
) {
    public static ListOccupationResponse from(final OccupationJpa occupationJpa) {
        return new ListOccupationResponse(
                occupationJpa.getId(),
                occupationJpa.getName()
        );
    }
}