package br.com.vicente.multitoolsbackend.shared.infraestructure.config;

import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.delete.DeleteCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.list.ListCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.ecommerce.domain.category.CategoryGateway;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCase {

    private CategoryGateway categoryGateway;

    public UseCase(final CategoryGateway categoryGateway) {
        this.categoryGateway = categoryGateway;
    }

    @Bean
    public RegisterCategoryUseCase registerCategoryUseCase(){
        return new RegisterCategoryUseCase(categoryGateway);
    }
    @Bean
    public DeleteCategoryUseCase deleteCategoryUseCase(){
        return new DeleteCategoryUseCase(categoryGateway);
    }

    @Bean
    public ListCategoryUseCase listCategoryUseCase(){
        return new ListCategoryUseCase(categoryGateway);
    }


}
