package br.com.vicente.multitoolsbackend.shared.infraestructure.config;

import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.delete.DeleteCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.get.GetCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.list.ListCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.register.RegisterCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.application.category.update.UpdateCategoryUseCase;
import br.com.vicente.multitoolsbackend.modules.crudcleanarchddd.domain.category.CategoryGateway;
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

    @Bean
    public GetCategoryUseCase getCategoryUseCase(){
        return new GetCategoryUseCase(categoryGateway);
    }
    @Bean
    public UpdateCategoryUseCase updateCategoryUseCase(){
        return new UpdateCategoryUseCase(categoryGateway);
    }




}
