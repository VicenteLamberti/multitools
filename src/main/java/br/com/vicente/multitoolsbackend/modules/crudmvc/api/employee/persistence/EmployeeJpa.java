package br.com.vicente.multitoolsbackend.modules.crudmvc.api.employee.persistence;

import br.com.vicente.multitoolsbackend.modules.crudmvc.api.occupation.persistence.OccupationJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Employee")
@Table(name = "EMPLOYEE", schema = "crudmvc")
public class EmployeeJpa {

    @Column(name = "ID", nullable = false)
    @Id
    private String id;
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "OCCUPATION_ID", nullable = false)
    private OccupationJpa occupation;


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public OccupationJpa getOccupation() {
        return occupation;
    }
}
