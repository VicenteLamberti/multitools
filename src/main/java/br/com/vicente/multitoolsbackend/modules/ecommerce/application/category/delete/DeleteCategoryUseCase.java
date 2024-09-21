package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.delete;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.delete.models.DeleteCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.UseCaseIn;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;
import br.com.vicente.multitoolsbackend.shared.domain.exception.ValidateNotification;

public class DeleteCategoryUseCase implements UseCaseIn<DeleteCategoryCommand> {

    private final CategoryGateway categoryGateway;

    public DeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void execute(final DeleteCategoryCommand cmd){
        final CategoryID id = cmd.id();
        final Notification notification = Notification.create(Strings.UNABLE_DELETE_CATEGORY);

        final Category category = notification.validate(()->categoryGateway.getByID(id));
        ValidateNotification.useCaseCheckHasErrors(notification, Strings.UNABLE_DELETE_CATEGORY);

        notification.validateVoid(()->category.delete());
        ValidateNotification.useCaseCheckHasErrors(notification,Strings.UNABLE_DELETE_CATEGORY);

        notification.validateVoid(()->categoryGateway.delete(category));
        ValidateNotification.useCaseCheckHasErrors(notification,Strings.UNABLE_DELETE_CATEGORY);
    }
}
