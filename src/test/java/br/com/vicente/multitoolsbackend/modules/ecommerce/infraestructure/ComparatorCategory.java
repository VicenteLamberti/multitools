package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.Category;
import br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure.category.persistence.CategoryJpa;
import org.junit.jupiter.api.Assertions;

public class ComparatorCategory {

    public static void compare(final CategoryJpa expectedCategoryJpa, final Category actualCategory) {

        Assertions.assertNotNull(actualCategory);
        Assertions.assertNotNull(expectedCategoryJpa);

        Assertions.assertEquals(expectedCategoryJpa.getId(), actualCategory.getId().getValue());
        Assertions.assertEquals(expectedCategoryJpa.getName(), actualCategory.getName());
        Assertions.assertEquals(expectedCategoryJpa.getProducts().size(), actualCategory.getProducts().size());


    }
}
