package br.com.vicente.multitoolsbackend.shared.domain.exception;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class DomainException extends NoStacktraceException{

    protected final List<String>errors;
    public DomainException(final String message, final List<String> errors) {
        super(message);
        this.errors = errors;
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }
}
