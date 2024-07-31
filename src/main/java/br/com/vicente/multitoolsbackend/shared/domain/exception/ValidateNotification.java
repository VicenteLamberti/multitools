package br.com.vicente.multitoolsbackend.shared.domain.exception;

public class ValidateNotification {
    private ValidateNotification() {
    }

    public static void useCaseCheckHasErrors(
            final Notification notification,
            final String exceptionMessage
    ) {
        if (notification.hasError()) {
            throw new UseCaseException(exceptionMessage, notification.getErrors());
        }
    }
}
