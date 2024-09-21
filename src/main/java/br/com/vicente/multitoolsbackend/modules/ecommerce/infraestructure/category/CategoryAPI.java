package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category;

import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.RegisterCategoryRequest;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.create.models.RegisterCategoryResponse;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.list.ListCategoryResponse;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.get.models.GetCategoryResponse;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.update.models.UpdateCategoryRequest;
import br.com.vicente.multitoolsbackend.shared.Strings;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(Strings.URL_CATEGORIES)
public interface CategoryAPI {

    @PostMapping
    ResponseEntity<RegisterCategoryResponse> create(final @RequestBody RegisterCategoryRequest request);

    @DeleteMapping(value = Strings.BAR + Strings.KEYS_ID)
    ResponseEntity<Void>delete(@PathVariable(name = Strings.ID) String id);

    @GetMapping
    ResponseEntity<List<ListCategoryResponse>> list();
    @GetMapping(value = Strings.BAR + Strings.KEYS_ID)
    ResponseEntity <GetCategoryResponse> get(@PathVariable(name = Strings.ID) String id);

    @PutMapping(value = Strings.BAR + Strings.KEYS_ID)
    ResponseEntity<Void> update(@PathVariable(name = Strings.ID) String id, @RequestBody UpdateCategoryRequest request);
}
