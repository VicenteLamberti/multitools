package br.com.vicente.multitoolsbackend.shared.domain;

import java.util.List;
import java.util.Objects;

public abstract class ValidatorCustom<T> {

    protected final T object;

    protected ValidatorCustom(final T object) {
        this.object = Objects.requireNonNull(object);
    }

    public abstract List<String> validate();

}