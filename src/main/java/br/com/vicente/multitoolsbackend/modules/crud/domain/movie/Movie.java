package br.com.vicente.multitoolsbackend.modules.crud.domain.movie;

import br.com.vicente.multitoolsbackend.shared.domain.Entity;
import br.com.vicente.multitoolsbackend.modules.crud.domain.category.Category;

public class Movie extends Entity<MovieID> {

    private String name;
    private Category category;

    public Movie(final MovieBuilder builder) {
        super(builder.getId());
        this.name = builder.getName();
        this.category = builder.getCategory();
    }

    @Override
    public void selfValidate() {

    }

    public MovieID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }
}
