package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product.Product;

import java.util.ArrayList;
import java.util.List;

public class CategoryDummyBuilder {
   private final CategoryBuilder categoryBuilder;

    public CategoryDummyBuilder(final CategoryBuilder categoryBuilder) {
        this.categoryBuilder = categoryBuilder;
    }


    public CategoryBuilder builder(){
        this.categoryBuilder
                .withId(CategoryID.generate())
                .withName("Category Name")
                .withDeleted(false)
                .withProducts(new ArrayList<>());
        return this.categoryBuilder;
    }

    public Category build(){
        return new Category(this.categoryBuilder);
    }


}
