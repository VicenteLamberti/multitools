package br.com.vicente.multitoolsbackend.modules.crudmvc.service;

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
    public static final String CATEGORY_NOT_FOUND = "Category not found";
    private final OccupationRepository occupationRepository;

    public OccupationService(final OccupationRepository occupationRepository) {
        this.occupationRepository = occupationRepository;
    }

    public void delete(final Long id) {

        final OccupationJpa occupation = occupationRepository.
                findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));

        occupation.setDeleted(true);
        occupation.setDeletedAt(LocalDateTime.now());
        occupationRepository.delete(occupation);


    }

    public GetOccupationResponse get(final Long id) {
        final OccupationJpa occupation = occupationRepository.
                findById(id)
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));

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
                .orElseThrow(() -> new NotFoundException(CATEGORY_NOT_FOUND));

        occupation.setName(request.name());
        occupation.setUpdatedAt(LocalDateTime.now());
        occupationRepository.save(occupation);
    }
}



