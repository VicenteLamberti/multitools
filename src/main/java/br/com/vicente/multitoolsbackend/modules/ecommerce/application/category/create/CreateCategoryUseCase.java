package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.UseCase;
import org.springframework.stereotype.Component;

@Component
public class CreateCategoryUseCase implements UseCase<CreateCategoryCommand, CreateCategoryOutput> {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public CreateCategoryOutput execute(final CreateCategoryCommand cmd) {
        final String name = cmd.name();
        final Category category = Category.create(name);
        categoryGateway.create(category);
        return CreateCategoryOutput.from(category);
    }
}
