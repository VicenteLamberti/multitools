package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.product.persistence.ProductJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Category")
@Table(name = "CATEGORY", schema = "crudcleanarchddd")
public class CategoryJpa {
    @Column(name = "ID", nullable = false)
    @Id
    private String id;
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private Set<ProductJpa> products;
    @Column(name = "DELETED", nullable = false)
    private boolean deleted;
    @Column(name = "UPDATED_AT")
    private LocalDateTime updatedAt;
    @Column(name = "DELETED_AT")
    private LocalDateTime deletedAt;

    protected CategoryJpa() {
    }

    private CategoryJpa(
            final String id,
            final String name,
            final Set<ProductJpa> products,
            final boolean deleted,
            final LocalDateTime deletedAt,
            final LocalDateTime updatedAt
    ) {
        this.id = id;
        this.name = name;
        this.products = products;
        this.deleted = deleted;
        this.deletedAt = deletedAt;
        this.updatedAt = updatedAt;
    }

    public static CategoryJpa from(final Category category) {
        return new CategoryJpa(
                category.getId().getValue(),
                category.getName(),
                category.getProducts().stream().map(ProductJpa::from).collect(Collectors.toSet()),
                category.isDeleted(),
                category.getDeletedAt(),
                category.getUpdatedAt()

        );
    }

    public Category toAggregate() {
        return CategoryBuilder.builder()
                .withId(CategoryID.from(getId()))
                .withName(getName())
                .withDeleted(isDeleted())
                //TODO ver porque com List ele considera que a lista está inicializada e da pau
                .withProducts(Hibernate.isInitialized(getProducts()) ? getProducts().stream().map(ProductJpa::toAggregate).toList() : new ArrayList<>())
                .withUpdatedAt(getUpdatedAt())
                .withDeletedAt(getDeletedAt())
                .rebuild();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    //TODO se eu colocar para retornar unmodified da problema de lazy
    public Set<ProductJpa> getProducts() {
        return products;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }
}
