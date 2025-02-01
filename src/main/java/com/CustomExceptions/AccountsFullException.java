package com.CustomExceptions;

public class AccountsFullException extends RuntimeException {
    public AccountsFullException(String message) {
        super(message);
    }
}
