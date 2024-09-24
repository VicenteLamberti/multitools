package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import java.util.List;

public interface CategoryGateway {
    void register(final Category category);
    Category getByID(final CategoryID id);

    void delete(final Category category);

    List<Category> list();

    void update(Category category);
}
