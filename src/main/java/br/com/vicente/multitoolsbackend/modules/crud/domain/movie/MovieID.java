package br.com.vicente.multitoolsbackend.modules.crud.domain.movie;

import br.com.vicente.multitoolsbackend.shared.domain.Identifier;
import br.com.vicente.multitoolsbackend.shared.domain.utils.IdUtils;

import java.util.Objects;

public class MovieID extends Identifier {
    private final String value;

    private MovieID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static MovieID generate() {
        return MovieID.from(IdUtils.uuid());
    }

    public static MovieID from(final String id) {
        return id == null ? null : new MovieID(id);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MovieID that = (MovieID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
