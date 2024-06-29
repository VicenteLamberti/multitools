package br.com.vicente.multitoolsbackend.modules.crud.infraestructure.movie.persistence;

import br.com.vicente.multitoolsbackend.modules.crud.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crud.infraestructure.category.persistence.CategoryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

import java.util.List;

@Entity(name = "Movie")
@Table(name = "MOVIE")
public class MovieJpa {

    @Column(name = "ID", nullable = false)
    @Id
    private String id;
    @Column(name = "NAME", nullable = false, length = 255)
    @Id
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryJpa categoryJpa;
}
