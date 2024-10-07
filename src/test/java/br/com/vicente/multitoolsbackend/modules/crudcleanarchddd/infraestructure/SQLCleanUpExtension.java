package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.category.persistence.CategoryRepository;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.context.ApplicationContext;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collection;
import java.util.List;

public class SQLCleanUpExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        ApplicationContext applicationContext = SpringExtension.getApplicationContext(context);
        cleanUp(
                List.of(
                        applicationContext.getBean(CategoryRepository.class)

                )
        );

    }

    private void cleanUp(Collection<CrudRepository> repositories) {

        repositories.forEach(CrudRepository::deleteAll);
    }
}