package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.update.models;


import br.com.vicente.multitoolsbackend.shared.Strings;
import com.fasterxml.jackson.annotation.JsonProperty;

public record UpdateOccupationRequest(
        @JsonProperty(Strings.NAME)
        String name
) {
}
