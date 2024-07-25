package br.com.vicente.multitoolsbackend.shared.domain.exception;

import java.util.List;

public interface ValidationHandler {
    Notification append(String error);

    ValidationHandler append(ValidationHandler handler);

    <T> T validate(Validation<T> validation);

    List<String> getErrors();

    default boolean hasError() {
        return getErrors() != null && !getErrors().isEmpty();
    }

    interface Validation<T> {
        T validate();
    }
}