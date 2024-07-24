package br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.create;

import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RegisterCategoryUseCaseTest {
    @Mock
    private CategoryGateway categoryGateway;

    @InjectMocks
    private RegisterCategoryUseCase useCase;

    @Test
    void teste(){
        RegisterCategoryCommand cmd = RegisterCategoryCommand.with("Vic");

        RegisterCategoryOutput actualOutput = useCase.execute(cmd);

        parei aqui usar captor
        Assertions.assertNotNull(actualOutput);
    }

}