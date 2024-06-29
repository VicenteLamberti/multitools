package br.com.vicente.multitoolsbackend.modules.crud.application.category;

public record CreateCategoryCommand(
        String name
) {
    public static CreateCategoryCommand with(final String name){
        return new CreateCategoryCommand(name);
    }
}
