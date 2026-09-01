package br.com.ryanlucas.sistemalojaderacao.excecao;

public class InvalidoException extends RuntimeException {
    public InvalidoException(String message) {
        super(message);
    }
}
