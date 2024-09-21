package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.get;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

public record GetCategoryOutput(
        CategoryID id,
        String name
) {

    public static GetCategoryOutput from(final Category category){
        return new GetCategoryOutput(
                category.getId(),
                category.getName()
        );
    }
}
