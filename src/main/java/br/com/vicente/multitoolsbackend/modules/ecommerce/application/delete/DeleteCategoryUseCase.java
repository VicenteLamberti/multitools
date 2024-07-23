package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;

public class DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public CreateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void execute(final DeleteCategoryCommand cmd){
        final CategoryID id = cmd.id();
        final Category category = categoryGateway.getByID(id)
        category.delete();
        return CreateCategoryOutput.from(category);
    }
}
