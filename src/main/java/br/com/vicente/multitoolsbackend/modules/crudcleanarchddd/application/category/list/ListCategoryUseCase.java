package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.models.ListCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.Strings;
import br.com.vicente.multitoolsbackend.shared.UseCaseOut;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;
import br.com.vicente.multitoolsbackend.shared.domain.exception.ValidateNotification;

import java.util.List;
import java.util.Objects;

public class ListCategoryUseCase implements UseCaseOut<List<ListCategoryOutput>> {

    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public List<ListCategoryOutput> execute() {
        final Notification notification = Notification.create(Strings.UNABLE_LIST_CATEGORY);
        final List<Category> categories = notification.validate(categoryGateway::list);
        ValidateNotification.useCaseCheckHasErrors(notification, Strings.UNABLE_LIST_CATEGORY);
        return categories.stream().map(ListCategoryOutput::from).toList();
    }
}
