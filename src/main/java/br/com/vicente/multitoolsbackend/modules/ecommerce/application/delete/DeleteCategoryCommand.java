package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

public record DeleteCategoryCommand(
        CategoryID id
) {
    public static CreateCategoryCommand with(final String id){
        final CategoryID categoryID = CategoryID.from(id);
        return new DeleteCategoryCommand(categoryID);
    }
}
