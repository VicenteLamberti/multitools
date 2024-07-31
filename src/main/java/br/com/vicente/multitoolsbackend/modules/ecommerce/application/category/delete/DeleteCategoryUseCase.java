package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.delete;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.domain.ErrorMessages;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;
import br.com.vicente.multitoolsbackend.shared.domain.exception.ValidateNotification;

public class DeleteCategoryUseCase {

    private final CategoryGateway categoryGateway;

    public DeleteCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void execute(final DeleteCategoryCommand cmd){
        final CategoryID id = cmd.id();
        final Notification notification = Notification.create();

        final Category category = notification.validate(()->categoryGateway.getByID(id));
        ValidateNotification.useCaseCheckHasErrors(notification, Strings.UNABLE_DELETE_CATEGORY);
        notification.validate(()->{
            category.delete();
            return null;
        });
        ValidateNotification.useCaseCheckHasErrors(notification,Strings.UNABLE_DELETE_CATEGORY);
        notification.validate(()->{
            categoryGateway.delete();
            return null;
        });
        ValidateNotification.useCaseCheckHasErrors(notification,Strings.UNABLE_DELETE_CATEGORY);
    }
}
