package br.com.vicente.multitoolsbackend.modules.crud.infraestructure.category.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name = "Category")
@Table(name = "CATEGORY")
public class CategoryJpa {

    private String id;
    private String name;
    private List<MovieJpa> movies;
}
