package com.CustomExceptions;

public class AccountNotEmptyException extends RuntimeException {
    public AccountNotEmptyException(String message) {
        super(message);
    }
}
