package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import java.util.ArrayList;

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

    public Category dummy(){
        return new Category(
                this.categoryBuilder,
                this.categoryBuilder.getId()
        );
    }


}
