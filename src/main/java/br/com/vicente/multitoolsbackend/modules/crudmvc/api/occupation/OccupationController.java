package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation;

import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models.RegisterOccupationRequest;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models.RegisterOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.get.models.GetOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.list.models.ListOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.update.models.UpdateOccupationRequest;
import br.com.vicente.multitoolsbackend.modules.crudmvc.service.OccupationService;
import br.com.vicente.multitoolsbackend.shared.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class OccupationController implements OccupationAPI {
    private final OccupationService occupationService;

    public OccupationController(final OccupationService occupationService) {
        this.occupationService = occupationService;
    }

    @Override
    public ResponseEntity<RegisterOccupationResponse> create(final RegisterOccupationRequest request) {
        RegisterOccupationResponse register = occupationService.register(request);
        return ResponseEntity.created(URI.create(Strings.URL_OCCUPATION + Strings.BAR + register.id())).body(register);
    }

    @Override
    public ResponseEntity<Void> delete(final Long id) {
        occupationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ListOccupationResponse>> list() {
        return ResponseEntity.ok(occupationService.list());
    }

    @Override
    public ResponseEntity<GetOccupationResponse> get(final Long id) {
        return ResponseEntity.ok(occupationService.get(id));
    }

    @Override
    public ResponseEntity<Void> update(
            final Long id,
            final UpdateOccupationRequest request
    ) {
        occupationService.update(id, request);
        return ResponseEntity.noContent().build();
    }
}
