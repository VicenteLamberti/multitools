package br.com.vicente.multitoolsbackend.shared;

public interface UseCaseIn<INPUT> {
    void execute(final INPUT cmd);
}
