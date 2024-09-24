package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models;

import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record RegisterOccupationRequest(
        @JsonProperty(Strings.NAME)
        String name
) {
    public static RegisterOccupationRequest with(final String name) {
        return new RegisterOccupationRequest(
                name
        );
    }
}
