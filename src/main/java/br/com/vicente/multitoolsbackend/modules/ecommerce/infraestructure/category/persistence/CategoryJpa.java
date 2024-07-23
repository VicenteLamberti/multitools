package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.product.persistence.ProductJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.Collections;
import java.util.List;

@Entity(name = "Category")
@Table(name = "CATEGORY")
public class CategoryJpa {
    @Column(name = "ID", nullable = false)
    @Id
    private String id;
    @Column(name = "NAME", nullable = false, length = 255)
    private String name;
    @OneToMany(mappedBy = "category", fetch = FetchType.LAZY)
    private List<ProductJpa> products;

    protected CategoryJpa() {
    }

    private CategoryJpa(
            final String id,
            final String name,
            final List<ProductJpa> products
    ) {
        this.id = id;
        this.name = name;
        this.products = products;
    }

    public static CategoryJpa from(final Category category) {
        return new CategoryJpa(
                category.getId().getValue(),
                category.getName(),
                category.getProducts().stream().map(ProductJpa::from).toList()
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<ProductJpa> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
