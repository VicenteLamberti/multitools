package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models;

import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;

public record RegisterOccupationRequest(
        @JsonProperty(Strings.NAME)
        @NotNull
        String name
) {
}
