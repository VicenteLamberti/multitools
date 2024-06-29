package br.com.vicente.multitoolsbackend.modules.crud.application.category;

import br.com.vicente.multitoolsbackend.modules.crud.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.crud.domain.category.CategoryID;

import java.util.List;

public record CreateCategoryOutput(
        CategoryID id,
        String name,
        List<CreateCategoryMovieOutput> movies
) {

    public static CreateCategoryOutput from(final Category category){
        return new CreateCategoryOutput(
                category.getId(),
                category.getName(),
                category.getMovies().stream().map(CreateCategoryMovieOutput::from).toList()
        );
    }
}
