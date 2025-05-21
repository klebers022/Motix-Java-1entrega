package br.com.motix.exceptions;

import jakarta.validation.constraints.NotNull;

public class ExistingUserErrorException extends RuntimeException {
    public ExistingUserErrorException(String message) {
        super(message);
    }
}
