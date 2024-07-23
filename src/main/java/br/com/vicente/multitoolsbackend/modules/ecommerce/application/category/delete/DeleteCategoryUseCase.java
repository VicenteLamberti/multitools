package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.delete;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

public class DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void execute(final DeleteCategoryCommand cmd){
        final CategoryID id = cmd.id();
        final Category category = categoryGateway.getByID(id);
        category.delete();
    }
}
