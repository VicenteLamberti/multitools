package br.com.vicente.multitoolsbackend.modules.crud.domain.category;

import br.com.vicente.multitoolsbackend.modules.crud.domain.movie.Movie;

import java.util.List;

public class Category {
    private String id;
    private String name;
    private List<Movie> movies;
}
