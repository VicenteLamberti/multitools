package br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence;

import br.com.vicente.multitoolsbackend.modules.crudmvc.api.employee.persistence.EmployeeJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.Set;

@Entity(name = "Occupation")
@Table(name = "OCCUPATION", schema = "crudmvc")
public class OccupationJpa {
    @Column(name = "ID", nullable = false)
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;
    @OneToMany(mappedBy = "occupation", fetch = FetchType.LAZY)
    private Set<EmployeeJpa> employees;
    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;


    public Long getId() {
        return id;
    }

    public OccupationJpa setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public OccupationJpa setName(String name) {
        this.name = name;
        return this;
    }

    public Set<EmployeeJpa> getEmployees() {
        return employees;
    }

    public OccupationJpa setEmployees(Set<EmployeeJpa> employees) {
        this.employees = employees;
        return this;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public OccupationJpa setDeleted(boolean deleted) {
        this.deleted = deleted;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public OccupationJpa setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public OccupationJpa setDeletedAt(LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }
}
