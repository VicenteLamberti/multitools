package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.product.persistence;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.Product;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.ProductBuilder;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.ProductID;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryJpa;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity(name = "Product")
@Table(name = "PRODUCT", schema = "crudcleanarchddd")
public class ProductJpa {

    @Column(name = "ID", nullable = false)
    @Id
    private String id;
    @Column(name = "NAME", nullable = false, length = 255)
    @Id
    private String name;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    private CategoryJpa category;

    protected ProductJpa() {

    }

    public ProductJpa(
            final String id,
            final String name,
            final CategoryJpa category
    ) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public static ProductJpa from(final Product product){
        return new ProductJpa(
                product.getId().getValue(),
                product.getName(),
                CategoryJpa.from(product.getCategory())
        );
    }

    public Product toAggregate(){
        return ProductBuilder.builder()
                .withId(ProductID.from(getId()))
                .withName(getName())
                .withCategory(getCategory().toAggregate())
                .rebuild();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public CategoryJpa getCategory() {
        return category;
    }
}
