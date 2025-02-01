package com.CustomExceptions;

public class AccountOverflowException extends RuntimeException {
    public AccountOverflowException(String message) {
        super(message);
    }
}
