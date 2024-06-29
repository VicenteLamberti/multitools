package br.com.vicente.multitoolsbackend.modules.crud.application.category.create;

import br.com.vicente.multitoolsbackend.modules.crud.domain.movie.Movie;
import br.com.vicente.multitoolsbackend.modules.crud.domain.movie.MovieID;

public record CreateCategoryMovieOutput(
        MovieID id,
        String name
) {
    public static CreateCategoryMovieOutput from(final Movie movie){
        return new CreateCategoryMovieOutput(
                movie.getId(),
                movie.getName()
        );
    }
}
