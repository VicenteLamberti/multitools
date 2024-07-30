package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.RegisterCategoryRequest;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.RegisterCategoryResponse;
import br.com.vicente.multitoolsbackend.shared.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.Objects;

@RestController
public class CategoryController implements CategoryAPI {
    private final RegisterCategoryUseCase registerCategoryUseCase;

    public CategoryController(final RegisterCategoryUseCase registerCategoryUseCase) {
        this.registerCategoryUseCase = Objects.requireNonNull(registerCategoryUseCase);
    }

    @Override
    public ResponseEntity<RegisterCategoryResponse> create(final RegisterCategoryRequest request) {
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(request.name());
        final RegisterCategoryOutput output = registerCategoryUseCase.execute(cmd);
        return ResponseEntity.created(URI.create(Strings.URL_CATEGORIES + Strings.BAR + output.id().getValue())).body(RegisterCategoryResponse.from(output));
    }
}
