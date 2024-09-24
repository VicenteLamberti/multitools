package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccupationRepository extends JpaRepository<OccupationJpa, Long> {
}
