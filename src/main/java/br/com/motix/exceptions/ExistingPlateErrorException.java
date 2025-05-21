package br.com.motix.exceptions;

public class ExistingPlateErrorException extends RuntimeException{

    public ExistingPlateErrorException(String message) {
        super(message);
    }
}
