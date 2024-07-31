package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.UseCase;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;
import br.com.vicente.multitoolsbackend.shared.domain.exception.UseCaseException;
import org.springframework.stereotype.Component;

public class RegisterCategoryUseCase implements UseCase<RegisterCategoryCommand, RegisterCategoryOutput> {

    private final CategoryGateway categoryGateway;

    public RegisterCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Override
    public RegisterCategoryOutput execute(final RegisterCategoryCommand cmd) {
        final String name = cmd.name();
        final Notification notification = Notification.create();
        final Category category = notification.validate(() -> Category.register(name));
        validateErrors(notification);
        notification.validate(()->{
            categoryGateway.register(category);
            return null;
        });
        validateErrors(notification);
        return RegisterCategoryOutput.from(category);
    }

    private static void validateErrors(final Notification notification) {
        if(notification.hasError()){
            //TODO colocar tudo no mesmo arquivo pq tenho que traduzir tudo
            throw new UseCaseException(Strings.UNABLE_REGISTER_CATEGORY, notification.getErrors());
        }
    }
}
