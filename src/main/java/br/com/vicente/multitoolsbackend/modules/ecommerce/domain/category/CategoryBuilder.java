package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;

import java.util.List;

public class CategoryBuilder {
    private CategoryID id;
    private String name;
    private List<Product> products;
    private boolean deleted;

    public static CategoryBuilder builder(){
        return new CategoryBuilder();
    }

    public Category build(){
        return new Category(this);
    }
    public Category generateFrom(){
        return new Category(this, id);
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

    public boolean getDeleted() {
        return deleted;
    }

    public CategoryBuilder withDeleted(final boolean deleted) {
        this.deleted = deleted;
        return this;
    }


    
}
