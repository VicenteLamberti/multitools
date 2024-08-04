package br.com.vicente.multitoolsbackend.shared.domain.exception;

import br.com.vicente.multitoolsbackend.shared.usecase.exception.UseCaseException;

import java.util.List;

public class ValidateNotification {
    private ValidateNotification() {
    }

    public static void useCaseCheckHasErrors(
            final Notification notification,
            final String exceptionMessage
    ) {
        if (notification.hasError()) {
            final List<String> errors = notification.getErrors();
            //TODO adicionar logger
            errors.forEach(error-> System.out.println("LOGGER ERROR - " + error));
            throw new UseCaseException(exceptionMessage, errors);
        }
    }
}
