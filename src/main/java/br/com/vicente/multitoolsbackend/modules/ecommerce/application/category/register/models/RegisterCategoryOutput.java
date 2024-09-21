package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.models;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

public record RegisterCategoryOutput(
        CategoryID id
) {

    public static RegisterCategoryOutput from(final Category category){
        return new RegisterCategoryOutput(
                category.getId()
        );
    }
}
