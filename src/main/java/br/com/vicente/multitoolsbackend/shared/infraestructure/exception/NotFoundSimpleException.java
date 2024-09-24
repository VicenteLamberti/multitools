package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

public class NotFoundSimpleException extends RuntimeException {

    public NotFoundSimpleException(final String message) {
        super(message);

    }

}
