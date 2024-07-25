package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.CreateCategoryRequest;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.CreateCategoryResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class CategoryController implements CategoryAPI {
    private final RegisterCategoryUseCase registerCategoryUseCase;

    public CategoryController(final RegisterCategoryUseCase registerCategoryUseCase) {
        this.registerCategoryUseCase = Objects.requireNonNull(registerCategoryUseCase);
    }

    @Override
    public ResponseEntity<CreateCategoryResponse> create(final CreateCategoryRequest request) {
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(request.name());
        final RegisterCategoryOutput output = registerCategoryUseCase.execute(cmd);
        return ResponseEntity.ok(CreateCategoryResponse.from(output));
    }
}
