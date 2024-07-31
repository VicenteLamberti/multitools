package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product;

import br.com.vicente.multitoolsbackend.shared.domain.Entity;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;

public class Product extends Entity<ProductID> {

    private String name;
    private Category category;

    protected Product(final ProductBuilder builder) {
        super(ProductID.generate());
        this.name = builder.getName();
        this.category = builder.getCategory();
    }
    protected Product(final ProductBuilder builder, final ProductID id) {
        super(id);
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
