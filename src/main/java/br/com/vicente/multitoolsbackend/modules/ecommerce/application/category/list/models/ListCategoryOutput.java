package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.list.models;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

public record ListCategoryOutput(
        CategoryID id,
        String name
) {

    public static ListCategoryOutput from(final Category category){
        return new ListCategoryOutput(
                category.getId(),
                category.getName()
        );
    }
}
