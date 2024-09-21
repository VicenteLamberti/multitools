package br.com.vicente.multitoolsbackend.shared;

public interface UseCaseInOut<INPUT, OUTPUT> {
    OUTPUT execute(INPUT cmd);
}
