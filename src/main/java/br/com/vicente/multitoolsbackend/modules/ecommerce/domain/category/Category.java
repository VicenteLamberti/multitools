package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.domain.Entity;
import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Category extends Entity<CategoryID> {
    private String name;
    private List<Product> products;
    private boolean deleted;
    private LocalDateTime deletedAt;
    private LocalDateTime updatedAt;

    protected Category(final CategoryBuilder builder) {
        super(CategoryID.generate());
        this.name = builder.getName();
        this.products = new ArrayList<>();
        this.deleted = false;
        this.deletedAt = null;
        this.updatedAt = null;

        selfValidate();
    }

    protected Category(
            final CategoryBuilder builder,
            final CategoryID id
    ) {
        super(id);
        this.name = builder.getName();
        this.products = builder.getProducts();
        this.deleted = builder.getDeleted();
        this.deletedAt = builder.getDeletedAt();
        this.updatedAt = builder.getUpdatedAt();

        selfValidate();
    }


    public static Category register(final String name) {
        return CategoryBuilder.builder()
                .withName(name)
                .build();
    }

    public void delete() {
        if (!this.products.isEmpty()) {
            List<String> errors = new ArrayList<>();
            errors.add(Strings.PRODUCTS_LINKED);
            throw new DomainException(Strings.ERROR_AT_DELETE_CATEGORY, errors);
        }
        this.deleted = true;
        this.deletedAt = LocalDateTime.now();
        selfValidate();
    }

    public void update(final String name) {
        this.name = name;
        this.updatedAt = LocalDateTime.now();
        selfValidate();
    }

    //todo mudar para inativo
//    public void inativar() {
//        if (!this.products.isEmpty()) {
//            List<String> errors = new ArrayList<>();
//            errors.add(ErrorMessages.PRODUCTS_LINKED);
//            throw new DomainException(ErrorMessages.AN_ERROR_OCCURRED_AT_DELETE_CATEGORY, errors);
//        }
//        if(this.deleted){
//            List<String> errors = new ArrayList<>();
//            errors.add(ErrorMessages.ALREADY_DELETED);
//            throw new DomainException(ErrorMessages.AN_ERROR_OCCURRED_AT_DELETE_CATEGORY, errors);
//        }
//    }

    @Override
    public void selfValidate() {
        final List<String> errors = new CategoryValidator(this).validate();
        if (!errors.isEmpty()) {
            throw new DomainException(Strings.ERROR_AT_INSTANTIATE_CATEGORY, errors);
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

    public boolean isDeleted() {
        return deleted;
    }

    public LocalDateTime getDeletedAt() {
        return deletedAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }
}
