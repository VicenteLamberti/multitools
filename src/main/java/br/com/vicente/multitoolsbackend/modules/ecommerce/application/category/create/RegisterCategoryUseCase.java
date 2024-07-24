package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.UseCase;
import org.springframework.stereotype.Component;

@Component
public class RegisterCategoryUseCase implements UseCase<RegisterCategoryCommand, RegisterCategoryOutput> {

    private final CategoryGateway categoryGateway;

    public RegisterCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public RegisterCategoryOutput execute(final RegisterCategoryCommand cmd) {
        final String name = cmd.name();
        final Category category = Category.register(name);
        categoryGateway.create(category);
        return RegisterCategoryOutput.from(category);
    }
}
