package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models;

public record RegisterCategoryCommand(
        String name
) {
    public static RegisterCategoryCommand with(final String name){
        return new RegisterCategoryCommand(name);
    }
}
