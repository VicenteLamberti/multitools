package br.com.vicente.multitoolsbackend.modules.crud.domain.category;

import br.com.vicente.multitoolsbackend.modules.crud.domain.movie.Movie;

import java.util.ArrayList;
import java.util.List;

public class CategoryBuilder {
    private CategoryID id;
    private String name;
    private List<Movie> movies;

    public static CategoryBuilder builder(){
        final CategoryBuilder builder = new CategoryBuilder();
        builder.movies = new ArrayList<>();
        return builder;
    }

    public Category build(){
        return new Category(this);
    }
    public CategoryID getId() {
        return id;
    }

    public CategoryBuilder withId(final CategoryID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CategoryBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public CategoryBuilder withMovies(final List<Movie> movies) {
        this.movies = movies;
        return this;
    }
}
