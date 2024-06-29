package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product;

import br.com.vicente.multitoolsbackend.shared.domain.Entity;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;

public class Product extends Entity<ProductID> {

    private String name;
    private Category category;

    public Product(final ProductBuilder builder) {
        super(builder.getId());
        this.name = builder.getName();
        this.category = builder.getCategory();
    }

    @Override
    public void selfValidate() {

    }

    public ProductID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
