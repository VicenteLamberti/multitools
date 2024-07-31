package br.com.vicente.multitoolsbackend.shared.domain.exception;

public class ValidateNotification {
    private ValidateNotification() {
    }

    public static void useCaseCheckHasErrors(
            final Notification notification,
            final String exceptionMessage
    ) {
        if (notification.hasError()) {
            //TODO colocar tudo no mesmo arquivo pq tenho que traduzir tudo talvez na exception colocar a chave e a mensagem ingles
            throw new UseCaseException(exceptionMessage, notification.getErrors());
        }
    }
}
