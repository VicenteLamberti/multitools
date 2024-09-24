package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.models;


import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryID;

public record GetCategoryCommand(
        CategoryID id
) {
    public static GetCategoryCommand with(final String id){
        final CategoryID categoryID = CategoryID.from(id);
        return new GetCategoryCommand(categoryID);
    }
}
