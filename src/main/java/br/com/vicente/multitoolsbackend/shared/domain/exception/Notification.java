package br.com.vicente.multitoolsbackend.shared.domain.exception;


import br.com.vicente.multitoolsbackend.shared.usecase.exception.NotificationException;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Notification implements ValidationHandler {

    private final String message;
    private final List<String> errorMessages;

    private Notification(final String message, final List<String> errorMessages) {
        this.message = message;
        this.errorMessages = Objects.requireNonNull(errorMessages);
    }

    public static Notification create(final String message) {
        return new Notification(message, new ArrayList<>());
    }

    @Override
    public Notification append(final String errorMessage) {
        errorMessages.add(errorMessage);
        return this;
    }

    @Override
    public ValidationHandler append(ValidationHandler handler) {
        this.errorMessages.addAll(handler.getErrors());
        return this;
    }

    @Override
    public <T> T validate(final Validation<T> validation) {
        try {
            return validation.validate();
        } catch (final DomainException ex) {
            errorMessages.addAll(ex.getErrors());
        } catch (final Exception ex) {
            errorMessages.add(ex.getMessage());
        }
        return null;
    }

    @Override
    public void validateVoid(Runnable action) {
        validate(()->{
            action.run();
            return null;
        });
    }

    @Override
    public <T> T validateAndThrows(final Validation<T> validation) {
        T result = this.validate(validation);
        return result;
    }

    @Override
    public void validateVoidAndThrows(Runnable action) {
        validate(()->{
            action.run();
            return null;
        });
    }


    @Override
    public List<String> getErrors() {
        return errorMessages;
    }

    @Override
    public void throwsIfHasError() {
        if (this.hasError()) {
            throw new NotificationException(this.message, this.errorMessages);
        }
    }

}
