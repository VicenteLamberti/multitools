package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure;

import org.flywaydb.core.Flyway;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

public class SQLCleanUpExtension implements BeforeEachCallback {
    @Override
    public void beforeEach(ExtensionContext context) {
        Flyway flyway = SpringExtension.getApplicationContext(context)
                .getBean(Flyway.class);
        flyway.clean();
        flyway.migrate();
    }
}