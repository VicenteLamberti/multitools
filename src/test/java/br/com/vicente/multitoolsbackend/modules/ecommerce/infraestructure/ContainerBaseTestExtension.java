package br.com.vicente.multitoolsbackend.modules.ecommerce.infraestructure;

import org.junit.jupiter.api.extension.BeforeAllCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.testcontainers.containers.PostgreSQLContainer;

public class ContainerBaseTestExtension implements BeforeAllCallback {

    public static final PostgreSQLContainer
            POSTGRE_SQL_CONTAINER = new PostgreSQLContainer("postgres:latest")
            .withUsername("multitools")
            .withPassword("123456")
            .withDatabaseName("multitools");

    @Override
    public void beforeAll(ExtensionContext extensionContext) {
        if (!POSTGRE_SQL_CONTAINER.isRunning()) {
            POSTGRE_SQL_CONTAINER.start();
        }
    }
}
