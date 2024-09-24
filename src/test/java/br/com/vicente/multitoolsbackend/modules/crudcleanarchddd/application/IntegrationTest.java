package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure.SQLCleanUpExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@SpringBootTest(properties = "spring.flyway.clean-disabled=false")
@ActiveProfiles("test-integration")
@ExtendWith(value = {SQLCleanUpExtension.class})
public @interface IntegrationTest {

}
