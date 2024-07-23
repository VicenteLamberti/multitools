package br.com.vicente.multitoolsbackend.modules.ecommerce.application.delete;


import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

public record DeleteCategoryCommand(
        CategoryID id
) {
    public static DeleteCategoryCommand with(final String id){
        final CategoryID categoryID = CategoryID.from(id);
        return new DeleteCategoryCommand(categoryID);
    }
}
