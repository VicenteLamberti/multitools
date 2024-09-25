package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product.Product;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {
    private CategoryID id;
    private String name;
    private List<Product> products;
    private boolean deleted;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;
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
                .withProducts(new ArrayList<>())
                .withUpdatedAt(null)
                .withDeletedAt(null);

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

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public CategoryBuilder withDeletedAt(final LocalDateTime deletedAt) {
        this.deletedAt = deletedAt;
        return this;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public CategoryBuilder withUpdatedAt(final LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }
}
