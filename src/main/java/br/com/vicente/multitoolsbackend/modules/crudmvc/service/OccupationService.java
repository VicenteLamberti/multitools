package br.com.vicente.multitoolsbackend.modules.crudmvc.service;

import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.infraestructure.exception.NotFoundException;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models.RegisterOccupationRequest;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.create.models.RegisterOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.get.models.GetOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.list.models.ListOccupationResponse;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence.OccupationJpa;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence.OccupationRepository;
import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.update.models.UpdateOccupationRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OccupationService {
    private final OccupationRepository occupationRepository;

    public OccupationService(final OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }

    public void delete(final Long id) {

        final OccupationJpa occupation = occupationRepository.
                findById(id)
                .orElseThrow(() -> new NotFoundException(Strings.UNABLE_DELETE_OCCUPATION,List.of(Strings.OCCUPATION_NOT_FOUND + id)));

        occupation.setDeleted(true);
        occupation.setDeletedAt(LocalDateTime.now());
        occupationRepository.delete(occupation);


    }

    public GetOccupationResponse get(final Long id) {
        final OccupationJpa occupation = occupationRepository.
                findById(id)
                .orElseThrow(() -> new NotFoundException(Strings.UNABLE_GET_OCCUPATION,List.of(Strings.OCCUPATION_NOT_FOUND + id)));

        return GetOccupationResponse.from(occupation);

    }

    public List<ListOccupationResponse> list() {
        return occupationRepository.findAll().stream().map(ListOccupationResponse::from).toList();
    }

    @Transactional
    public RegisterOccupationResponse register(final RegisterOccupationRequest request) {

        final OccupationJpa occupationJpa = new OccupationJpa();
        occupationJpa.setName(request.name());
        occupationRepository.save(occupationJpa);
        return RegisterOccupationResponse.from(occupationJpa);
    }

    @Transactional
    public void update(final Long id, final UpdateOccupationRequest request) {

        final OccupationJpa occupation = occupationRepository.
                findById(id)
                .orElseThrow(() -> new NotFoundException(Strings.UNABLE_UPDATE_OCCUPATION,List.of(Strings.OCCUPATION_NOT_FOUND + id)));

        occupation.setName(request.name());
        occupation.setUpdatedAt(LocalDateTime.now());
        occupationRepository.save(occupation);
    }
}



