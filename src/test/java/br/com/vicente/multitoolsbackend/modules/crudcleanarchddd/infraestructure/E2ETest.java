package br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.infraestructure;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
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
@ActiveProfiles("test-e2e")
//@SpringBootTest(classes = WebServerConfig.class)
@SpringBootTest
//@ExtendWith(value = {SQLCleanUpExtension.class, CacheCleanUpExtension.class, ContainerBaseTestExtension.class})
@ExtendWith(value = {ContainerBaseTestExtension.class})
@AutoConfigureMockMvc
//@Tag("e2eTest")
public @interface E2ETest {
}