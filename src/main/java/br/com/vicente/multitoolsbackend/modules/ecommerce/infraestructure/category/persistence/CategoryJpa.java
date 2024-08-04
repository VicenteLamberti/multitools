package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryBuilder;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.product.persistence.ProductJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import org.hibernate.Hibernate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Entity(name = "Category")
@Table(name = "CATEGORY")
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

    protected CategoryJpa() {
    }

    private CategoryJpa(
            final String id,
            final String name,
            final Set<ProductJpa> products
    ) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public static CategoryJpa from(final Category category) {
        return new CategoryJpa(
                category.getId().getValue(),
                category.getName(),
                category.getProducts().stream().map(ProductJpa::from).collect(Collectors.toSet())
        );
    }

    public Category toAggregate(){
        return CategoryBuilder.builder()
                .withId(CategoryID.from(getId()))
                .withName(getName())
                .withDeleted(isDeleted())
                //TODO ver porque com List ele considera que a lista está inicializada e da pau
                .withProducts(Hibernate.isInitialized(getProducts()) ? getProducts().stream().map(ProductJpa::toAggregate).toList() : new ArrayList<>())
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
}
