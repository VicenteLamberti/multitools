package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

public interface CategoryGateway {
    //TODO colocar register
    void register(final Category category);
    Category getByID(final CategoryID id);
}
