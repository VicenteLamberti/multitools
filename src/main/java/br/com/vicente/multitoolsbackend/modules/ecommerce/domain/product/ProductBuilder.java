package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;

public class ProductBuilder {
    private ProductID id;
    private String name;
    private Category category;

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    public Product build() {
        return new Product(this);
    }

    public ProductID getId() {
        return id;
    }

    public ProductBuilder withId(final ProductID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public ProductBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public ProductBuilder withCategory(final Category category) {
        this.category = category;
        return this;
    }
}
