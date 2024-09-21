package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.list;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import br.com.vicente.multitoolsbackend.shared.UseCaseOut;
import br.com.vicente.multitoolsbackend.shared.domain.exception.Notification;

import java.util.List;
import java.util.Objects;

public class ListCategoryUseCase implements UseCaseOut<List<ListCategoryOutput>> {

    private final CategoryGateway categoryGateway;

    public ListCategoryUseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = Objects.requireNonNull(categoryGateway);
    }

    @Override
    public List<ListCategoryOutput> execute() {
        final Notification notification = Notification.create();
        final List<Category> categories = notification.validate(categoryGateway::list);
        return categories.stream().map(ListCategoryOutput::from).toList();
    }
}
