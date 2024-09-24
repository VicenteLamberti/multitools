package br.com.vicente.multitoolsbackend.shared.infraestructure.exception;

public record FormError(
        String field,
        String message
) {
}

