package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.models;


import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;

public record DeleteCategoryCommand(
        CategoryID id
) {
    public static DeleteCategoryCommand with(final String id){
        final CategoryID categoryID = CategoryID.from(id);
        return new DeleteCategoryCommand(categoryID);
    }
}
