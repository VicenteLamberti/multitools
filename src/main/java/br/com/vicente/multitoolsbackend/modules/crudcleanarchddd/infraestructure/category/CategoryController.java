package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.DeleteCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.models.DeleteCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.GetCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.models.GetCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.ListCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.models.RegisterCategoryOutput;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.UpdateCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.models.UpdateCategoryCommand;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.create.models.RegisterCategoryRequest;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.create.models.RegisterCategoryResponse;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.get.models.GetCategoryResponse;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.list.ListCategoryResponse;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.update.models.UpdateCategoryRequest;
import br.com.vicente.multitoolsbackend.shared.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
public class CategoryController implements CategoryAPI {
    private final RegisterCategoryUseCase registerCategoryUseCase;
    private final DeleteCategoryUseCase deleteCategoryUseCase;
    private final ListCategoryUseCase listCategoryUseCase;
    private final GetCategoryUseCase getCategoryUseCase;
    private final UpdateCategoryUseCase updateCategoryUseCase;

    public CategoryController(
            final RegisterCategoryUseCase registerCategoryUseCase,
            final DeleteCategoryUseCase deleteCategoryUseCase,
            final ListCategoryUseCase listCategoryUseCase,
            final GetCategoryUseCase getCategoryUseCase, UpdateCategoryUseCase updateCategoryUseCase
    ) {
        this.registerCategoryUseCase = Objects.requireNonNull(registerCategoryUseCase);
        this.deleteCategoryUseCase = Objects.requireNonNull(deleteCategoryUseCase);
        this.listCategoryUseCase = Objects.requireNonNull(listCategoryUseCase);
        this.getCategoryUseCase = Objects.requireNonNull(getCategoryUseCase);
        this.updateCategoryUseCase = Objects.requireNonNull(updateCategoryUseCase);
    }

    @Override
    public ResponseEntity<RegisterCategoryResponse> create(final RegisterCategoryRequest request) {
        final RegisterCategoryCommand cmd = RegisterCategoryCommand.with(request.name());
        final RegisterCategoryOutput output = registerCategoryUseCase.execute(cmd);
        return ResponseEntity.created(URI.create(Strings.URL_CATEGORIES + Strings.BAR + output.id().getValue())).body(RegisterCategoryResponse.from(output));
    }

    @Override
    public ResponseEntity<Void> delete(final String id) {
        final DeleteCategoryCommand cmd = DeleteCategoryCommand.with(id);
        deleteCategoryUseCase.execute(cmd);
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<List<ListCategoryResponse>> list() {
        return ResponseEntity.ok(listCategoryUseCase.execute().stream().map(ListCategoryResponse::from).toList());
    }

    @Override
    public ResponseEntity<GetCategoryResponse> get(final String id) {
        final GetCategoryCommand cmd = GetCategoryCommand.with(id);
        return ResponseEntity.ok(GetCategoryResponse.from(getCategoryUseCase.execute(cmd)));
    }

    @Override
    public ResponseEntity<Void> update(
            final String id,
            final UpdateCategoryRequest request
    ) {
        final String name = request.name();

        final UpdateCategoryCommand cmd = UpdateCategoryCommand.with(id, name);
        updateCategoryUseCase.execute(cmd);
        return ResponseEntity.noContent().build();
    }
}
