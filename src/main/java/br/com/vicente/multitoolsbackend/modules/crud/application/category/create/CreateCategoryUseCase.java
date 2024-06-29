package br.com.vicente.multitoolsbackend.modules.crud.application.category.create;

import br.com.vicente.multitoolsbackend.modules.crud.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crud.domain.category.CategoryGateway;

public class CreateCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public CreateCategoryOutput execute(CreateCategoryCommand cmd){
        final String name = cmd.name();
        final Category category = Category.create(name);
        categoryGateway.create();
        return CreateCategoryOutput.from(category);

    }
}
