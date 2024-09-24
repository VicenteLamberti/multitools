package br.com.vicente.multitoolsbackend.shared.usecase.exception;

import br.com.vicente.multitoolsbackend.shared.domain.exception.NoStacktraceException;

import java.util.Collections;
import java.util.List;

public class NotificationException extends NoStacktraceException {

    protected final List<String>errors;
    public NotificationException(final String message, final List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
