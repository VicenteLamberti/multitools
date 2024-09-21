package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.update.models;


import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

public record UpdateCategoryCommand(
        CategoryID id,
        String name
) {
    public static UpdateCategoryCommand with(final String id, String name){
        final CategoryID categoryID = CategoryID.from(id);
        return new UpdateCategoryCommand(categoryID,name);
    }
}
