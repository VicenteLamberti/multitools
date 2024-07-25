package br.com.vicente.multitoolsbackend.shared.domain.exception;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Notification implements ValidationHandler {

    private final List<String> errorMessages;

    private Notification(final List<String> errorMessages) {
        this.errorMessages = Objects.requireNonNull(errorMessages);
    }

    public static Notification create() {
        return new Notification(new ArrayList<>());
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
    public List<String> getErrors() {
        return errorMessages;
    }


}
