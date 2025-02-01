package com.CustomExceptions;

public class AccountDebtException extends RuntimeException {
    public AccountDebtException(String message) {
        super(message);
    }
}
