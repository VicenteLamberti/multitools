package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

public interface CategoryGateway {
    //TODO colocar register
    void create(final Category category);
    Category getByID(final CategoryID id);
}
