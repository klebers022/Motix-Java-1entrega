package br.com.motix.exceptions;

public class MotorcycleNotFoundException extends RuntimeException {

    public MotorcycleNotFoundException(String message) {
        super(message);
    }
}
