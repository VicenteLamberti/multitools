package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

import br.com.vicente.multitoolsbackend.shared.domain.exception.NoStacktraceException;

import java.util.ArrayList;
import java.util.List;

public class NotFoundException extends NoStacktraceException {
    private final List<String> errors;

    public NotFoundException(final String message, final List<String> errors) {
        super(message);
        this.errors = errors;
    }
    public NotFoundException(final String message) {
        super(message);
        this.errors = new ArrayList<>();
    }

    public List<String> getErrors() {
        return errors;
    }
}
