package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

public interface CategoryGateway {
    void register(final Category category);
    Category getByID(final CategoryID id);

    void delete(final Category category);
}
