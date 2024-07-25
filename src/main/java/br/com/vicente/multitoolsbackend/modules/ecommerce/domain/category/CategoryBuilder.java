package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {
    private CategoryID id;
    private String name;
    private List<Product> products;
    private boolean deleted;

    public static CategoryBuilder builder(){
        return new CategoryBuilder();
    }

    /**
     * Deve ser utilizado somente nos testes
     * @return CategoryBuilder
     */
    public static CategoryBuilder builderDummy(){
        final CategoryBuilder categoryBuilder = new CategoryBuilder();
        categoryBuilder
                .withId(CategoryID.generate())
                .withName("Category Name")
                .withDeleted(false)
                .withProducts(new ArrayList<>());
        return categoryBuilder;
    }

    protected Category build(){
        return new Category(this);
    }
    public Category rebuild(){
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
