package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.get;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.get.models.GetCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.get.models.GetCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.UseCaseInOut;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;
import br.com.vicente.multitoolsbackend.shared.domain.exception.ValidateNotification;

import java.util.Objects;

public class GetCategoryUseCase implements UseCaseInOut<GetCategoryCommand, GetCategoryOutput> {

    private final CategoryGateway categoryGateway;

    public GetCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public GetCategoryOutput execute(final GetCategoryCommand cmd) {
        final Notification notification = Notification.create(Strings.UNABLE_GET_CATEGORY);
        final CategoryID id = cmd.id();
        final Category category = notification.validate(()->categoryGateway.getByID(id));
        ValidateNotification.useCaseCheckHasErrors(notification, Strings.UNABLE_GET_CATEGORY);
        return GetCategoryOutput.from(category);
    }
}
