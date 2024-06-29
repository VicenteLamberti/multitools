package br.com.vicente.multitoolsbackend.modules.ecommerce.domain.product;

import br.com.vicente.multitoolsbackend.shared.domain.Identifier;
import br.com.vicente.multitoolsbackend.shared.domain.utils.IdUtils;

import java.util.Objects;

public class ProductID extends Identifier {
    private final String value;

    private ProductID(final String value) {
        Objects.requireNonNull(value);
        this.value = value;
    }

    public static ProductID generate() {
        return ProductID.from(IdUtils.uuid());
    }

    public static ProductID from(final String id) {
        return id == null ? null : new ProductID(id);
    }

    @Override
    public String getValue() {
        return value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductID that = (ProductID) o;
        return getValue().equals(that.getValue());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getValue());
    }
}
