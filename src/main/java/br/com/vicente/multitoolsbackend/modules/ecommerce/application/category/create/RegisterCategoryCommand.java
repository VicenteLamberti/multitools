package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

public record RegisterCategoryCommand(
        String name
) {
    public static RegisterCategoryCommand with(final String name){
        return new RegisterCategoryCommand(name);
    }
}
