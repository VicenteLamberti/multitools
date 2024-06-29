package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

import java.util.List;

public record CreateCategoryOutput(
        CategoryID id,
        String name
) {

    public static CreateCategoryOutput from(final Category category){
        return new CreateCategoryOutput(
                category.getId(),
                category.getName()
        );
    }
}
