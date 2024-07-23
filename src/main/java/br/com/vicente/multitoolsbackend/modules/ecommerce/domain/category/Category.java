package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;
import br.com.vicente.multitoolsbackend.shared.domain.Entity;
import br.com.vicente.multitoolsbackend.shared.domain.ErrorMessages;
import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;

import java.util.Collections;
import java.util.List;

public class Category extends Entity<CategoryID> {
    private String name;
    private List<Product> products;
    private boolean deleted;

    protected Category(final CategoryBuilder builder) {
        super(builder.getId());
        this.name = builder.getName();
        this.products = builder.getProducts();

        selfValidate();
    }

    public static Category create(final String name) {
        return CategoryBuilder.builder()
                .withId(CategoryID.generate())
                .withName(name)
                .build();
    }
    public void delete(){
        //todo
    }

    @Override
    public void selfValidate() {
        final List<String> errors = new CategoryValidator(this).validate();
        if (!errors.isEmpty()) {
            throw new DomainException(ErrorMessages.AN_ERROR_OCCURRED_AT_INSTANTIATE_CATEGORY, errors);
        }
    }

    public CategoryID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }
}
