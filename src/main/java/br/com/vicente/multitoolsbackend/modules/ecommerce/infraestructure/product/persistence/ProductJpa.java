package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.product.persistence;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence.CategoryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Product")
@Table(name = "PRODUCT")
public class ProductJpa {

    @Column(name = "ID", nullable = false)
    @Id
    private String id;
    @Column(name = "NAME", nullable = false, length = 255)
    @Id
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryJpa categoryJpa;

    protected ProductJpa() {

    }

    public ProductJpa(
            final String id,
            final String name,
            final CategoryJpa categoryJpa
    ) {
        this.id = id;
        this.name = name;
        this.categoryJpa = categoryJpa;
    }

    public static ProductJpa from(final Product product){
        return new ProductJpa(
                product.getId().getValue(),
                product.getName(),
                CategoryJpa.from(product.getCategory())
        );
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryJpa getCategoryJpa() {
        return categoryJpa;
    }
}
