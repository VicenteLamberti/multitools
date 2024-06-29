package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;

public class CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public CreateCategoryOutput execute(CreateCategoryCommand cmd){
        final String name = cmd.name();
        final Category category = Category.create(name);
        categoryGateway.create(category);
        return CreateCategoryOutput.from(category);
    }
}
