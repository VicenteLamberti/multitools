package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

import br.com.vicente.multitoolsbackend.shared.domain.exception.NoStacktraceException;

import java.util.Collections;
import java.util.List;

public class NotFoundException extends NoStacktraceException {

    protected final List<String>errors;
    public NotFoundException(final String message, final List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
