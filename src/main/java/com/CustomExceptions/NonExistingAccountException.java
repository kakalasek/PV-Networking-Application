package com.CustomExceptions;

public class NonExistingAccountException extends RuntimeException {
    public NonExistingAccountException(String message) {
        super(message);
    }
}
