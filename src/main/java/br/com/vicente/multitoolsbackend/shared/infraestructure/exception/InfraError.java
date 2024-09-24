package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

import br.com.vicente.multitoolsbackend.shared.usecase.exception.NotificationException;

import java.util.ArrayList;
import java.util.List;

public record InfraError (
        String message,
        List<String> errors
){

    public static InfraError from (final NotFoundException ex){
        return new InfraError(
                ex.getMessage(),
                new ArrayList<>()
        );
    }

    public static InfraError from (final NotificationException ex){
        return new InfraError(
                ex.getMessage(),
                ex.getErrors()
        );
    }
}
