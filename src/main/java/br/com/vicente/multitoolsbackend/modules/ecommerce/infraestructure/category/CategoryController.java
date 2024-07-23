package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create.CreateCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create.CreateCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create.CreateCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.CreateCategoryRequest;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.CreateCategoryResponse;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

public class CategoryController implements CategoryAPI {
    private final CreateCategoryUseCase createCategoryUseCase;

    public CategoryController(final CreateCategoryUseCase createCategoryUseCase) {
        this.createCategoryUseCase = Objects.requireNonNull(createCategoryUseCase);
    }

    @Override
    public ResponseEntity<CreateCategoryResponse> create(final CreateCategoryRequest request) {
        final CreateCategoryCommand cmd = CreateCategoryCommand.with(request.name());
        final CreateCategoryOutput output = createCategoryUseCase.execute(cmd);
        return ResponseEntity.ok(CreateCategoryResponse.from(output));
    }
}
