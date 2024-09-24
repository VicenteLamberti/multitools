package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

import br.com.vicente.multitoolsbackend.shared.domain.exception.NoStacktraceException;

public class NotFoundException extends NoStacktraceException {

    public NotFoundException(final String message) {
        super(message);
    }

}
