package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

public interface CategoryGateway {
    void register(final Category category);
    Category getByID(final CategoryID id);

    void delete(final Category category);

    List<Category> list();

    void update(Category category);
}
