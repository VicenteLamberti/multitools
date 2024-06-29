package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence;

import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.product.persistence.ProductJpa;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity(name = "Category")
@Table(name = "CATEGORY")
public class CategoryJpa {

    private String id;
    private String name;
    private List<ProductJpa> products;
}
