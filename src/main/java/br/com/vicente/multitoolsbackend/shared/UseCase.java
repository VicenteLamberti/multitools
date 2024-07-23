package br.com.vicente.multitoolsbackend.shared;

public interface UseCase<INPUT, OUTPUT> {
    OUTPUT execute(INPUT cmd);
}
