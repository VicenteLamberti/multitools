package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {
    private CategoryID id;
    private String name;
    private List<Product> products;

    public static CategoryBuilder builder(){
        final CategoryBuilder builder = new CategoryBuilder();
        builder.products = new ArrayList<>();
        return builder;
    }

    public Category build(){
        return new Category(this);
    }
    public CategoryID getId() {
        return id;
    }

    public CategoryBuilder withId(final CategoryID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public List<Product> getProducts() {
        return products;
    }

    public CategoryBuilder withProducts(final List<Product> products) {
        this.products = products;
        return this;
    }
}
