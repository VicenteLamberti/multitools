package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.shared.infraestructure.exception.NotFoundException;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CategoryDbGateway implements CategoryGateway {
    private final CategoryRepository categoryRepository;

    public CategoryDbGateway(final CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void register(final Category category) {
        final CategoryJpa categoryJpa = CategoryJpa.from(category);
        categoryRepository.save(categoryJpa);
    }

    @Override
    public Category getByID(final CategoryID id) {
        return categoryRepository
                .findById(id.getValue())
                .map(CategoryJpa::toAggregate)
                //TODO mudar mensagem
                .orElseThrow(() -> new NotFoundException("Erro no gateway", List.of("Categoria não encontrada " + id.getValue())));
    }

    @Override
    public void delete(final Category category) {
        final CategoryJpa categoryJpa = CategoryJpa.from(category);
        categoryRepository.delete(categoryJpa);

    }

    @Override
    public List<Category> list() {
        return categoryRepository.findAll().stream().map(CategoryJpa::toAggregate).toList();
    }
}
