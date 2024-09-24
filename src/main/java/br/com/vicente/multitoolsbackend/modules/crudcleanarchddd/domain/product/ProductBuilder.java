package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.product;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryBuilder;

public class ProductBuilder {
    private ProductID id;
    private String name;
    private Category category;

    public static ProductBuilder builder() {
        return new ProductBuilder();
    }

    /**
     * Deve ser utilizado somente nos testes
     * @return ProductBuilder
     */
    public static ProductBuilder builderDummy(){
        final ProductBuilder productBuilder = new ProductBuilder();
        productBuilder
                .withId(ProductID.generate())
                .withName("Product Name")
                .withCategory(CategoryBuilder.builderDummy().rebuild());

        return productBuilder;
    }


    public Product build() {
        return new Product(this);
    }
    public Product rebuild(){
        return new Product(this, id);
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
