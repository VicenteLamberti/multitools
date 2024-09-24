package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

import br.com.vicente.multitoolsbackend.shared.domain.exception.DomainException;
import br.com.vicente.multitoolsbackend.shared.usecase.exception.UseCaseException;

import java.util.ArrayList;
import java.util.List;

public record InfraError (
        String exceptionMessage,
        List<String> errors
){

    public static InfraError from (final NotFoundException ex){
        return new InfraError(
                ex.getMessage(),
                ex.getErrors()
        );
    }
    public static InfraError from (final NotFoundSimpleException ex){
        return new InfraError(
                ex.getMessage(),
                new ArrayList<>()
        );
    }

    public static InfraError from (final UseCaseException ex){
        return new InfraError(
                ex.getMessage(),
                ex.getErrors()
        );
    }
}
