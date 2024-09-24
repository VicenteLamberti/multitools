package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation;

import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models.RegisterOccupationRequest;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models.RegisterOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.get.models.GetOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.list.models.ListOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.update.models.UpdateOccupationRequest;
import br.com.vicente.multitoolsbackend.shared.Strings;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(Strings.URL_OCCUPATION)
public interface OccupationAPI {

    @PostMapping
    ResponseEntity<RegisterOccupationResponse> create(final @Valid @RequestBody RegisterOccupationRequest request);

    @DeleteMapping(value = Strings.BAR + Strings.KEYS_ID)
    ResponseEntity<Void>delete(@PathVariable(name = Strings.ID) Long id);

    @GetMapping
    ResponseEntity<List<ListOccupationResponse>> list();

    @GetMapping(value = Strings.BAR + Strings.KEYS_ID)
    ResponseEntity <GetOccupationResponse> get(@PathVariable(name = Strings.ID) Long id);

    @PutMapping(value = Strings.BAR + Strings.KEYS_ID)
    ResponseEntity<Void> update(@PathVariable(name = Strings.ID) Long id, @RequestBody UpdateOccupationRequest request);
}
