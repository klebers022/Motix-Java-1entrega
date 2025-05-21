package br.com.motix.exceptions;

public class ExistingUpdateErrorException extends RuntimeException {
    public ExistingUpdateErrorException(String message) {
        super(message);
    }
}
