package br.com.vicente.multitoolsbackend.modules.crud.domain.category;

import br.com.vicente.multitoolsbackend.shared.domain.Identifier;
import br.com.vicente.multitoolsbackend.shared.domain.utils.IdUtils;

import java.util.Objects;

public class CategoryID extends Identifier {
    private final String value;

    private CategoryID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static CategoryID generate() {
        return CategoryID.from(IdUtils.uuid());
    }

    public static CategoryID from(final String id) {
        return id == null ? null : new CategoryID(id);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoryID that = (CategoryID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
