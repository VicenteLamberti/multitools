package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryJpa, String> {
}
