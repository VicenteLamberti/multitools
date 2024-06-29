package br.com.vicente.multitoolsbackend.modules.crud.domain.movie;

import br.com.vicente.multitoolsbackend.modules.crud.domain.category.Category;

public class MovieBuilder {
    private MovieID id;
    private String name;
    private Category category;

    public static MovieBuilder builder() {
        return new MovieBuilder();
    }

    public Movie build() {
        return new Movie(this);
    }

    public MovieID getId() {
        return id;
    }

    public MovieBuilder withId(final MovieID id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public MovieBuilder withName(final String name) {
        this.name = name;
        return this;
    }

    public Category getCategory() {
        return category;
    }

    public MovieBuilder withCategory(final Category category) {
        this.category = category;
        return this;
    }
}
