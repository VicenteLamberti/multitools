package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.get.models;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryID;

import java.time.LocalDateTime;

public record GetCategoryOutput(
        CategoryID id,
        String name,
        boolean deleted,
        LocalDateTime deletedAt,
        LocalDateTime updatedAt
) {

    public static GetCategoryOutput from(final Category category){
        return new GetCategoryOutput(
                category.getId(),
                category.getName(),
                category.isDeleted(),
                category.getDeletedAt(),
                category.getUpdatedAt()
        );
    }
}
