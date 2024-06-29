package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

public record CreateCategoryCommand(
        String name
) {
    public static CreateCategoryCommand with(final String name){
        return new CreateCategoryCommand(name);
    }
}
