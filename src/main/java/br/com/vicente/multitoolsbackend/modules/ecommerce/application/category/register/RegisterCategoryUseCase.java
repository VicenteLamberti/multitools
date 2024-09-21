package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.models.RegisterCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.models.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.UseCaseInOut;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;
import br.com.vicente.multitoolsbackend.shared.domain.exception.ValidateNotification;

public class RegisterCategoryUseCase implements UseCaseInOut<RegisterCategoryCommand, RegisterCategoryOutput> {

    private final CategoryGateway categoryGateway;

    public RegisterCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public RegisterCategoryOutput execute(final RegisterCategoryCommand cmd) {
        final Notification notification = Notification.create(Strings.UNABLE_REGISTER_CATEGORY);

        final String name = cmd.name();

        final Category category = notification.validate(() -> Category.register(name));
        ValidateNotification.useCaseCheckHasErrors(notification, Strings.UNABLE_REGISTER_CATEGORY);

        notification.validateVoid(()->categoryGateway.register(category));
        ValidateNotification.useCaseCheckHasErrors(notification, Strings.UNABLE_REGISTER_CATEGORY);

        return RegisterCategoryOutput.from(category);
    }
}
