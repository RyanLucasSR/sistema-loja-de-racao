package br.com.ryanlucas.sistemalojaderacao.excecao;

public class CampoObrigatorioException extends RuntimeException {
    public CampoObrigatorioException(String message) {
        super(message);
    }
}
