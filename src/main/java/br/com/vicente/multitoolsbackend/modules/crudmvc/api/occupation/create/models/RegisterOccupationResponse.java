package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models;

import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence.OccupationJpa;
import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterOccupationResponse(
        @JsonProperty(Strings.ID)
        Long id
) {
    public static RegisterOccupationResponse from(final OccupationJpa occupationJpa) {
        return new RegisterOccupationResponse(
                occupationJpa.getId()
        );
    }
}