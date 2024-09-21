package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.update;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.delete.models.DeleteCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.update.models.UpdateCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.UseCaseIn;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;
import br.com.vicente.multitoolsbackend.shared.domain.exception.ValidateNotification;

public class UpdateCategoryUseCase implements UseCaseIn<UpdateCategoryCommand> {

    private final CategoryGateway categoryGateway;

    public UpdateCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    public void execute(final UpdateCategoryCommand cmd){
        final Notification notification = Notification.create();
        final CategoryID id = cmd.id();
        final String name = cmd.name();


        final Category category = notification.validate(()->categoryGateway.getByID(id));

        ValidateNotification.useCaseCheckHasErrors(notification, Strings.UNABLE_UPDATE_CATEGORY);

        notification.validate(()->{
            category.update(name);
            return null;
        });

        ValidateNotification.useCaseCheckHasErrors(notification,Strings.UNABLE_UPDATE_CATEGORY);

        notification.validate(()->{
            categoryGateway.update(category);
            return null;
        });

        ValidateNotification.useCaseCheckHasErrors(notification,Strings.UNABLE_DELETE_CATEGORY);
    }
}
