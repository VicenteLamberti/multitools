package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import org.springframework.stereotype.Component;

@Component
public class CategoryDbGateway implements CategoryGateway {
    private final CategoryRepository categoryRepository;

    public CategoryDbGateway(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void create(final Category category) {
        categoryRepository.save(category)

    }
}
