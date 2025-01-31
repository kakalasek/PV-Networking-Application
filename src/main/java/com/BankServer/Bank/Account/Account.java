package com.BankServer.Bank.Account;

/**
 * Represents a user account
 */
public class Account {
    private final int number;
    private long balance;

    public Account(int number, long balance){
        this.number = number;
        this.balance = balance;
    }

    public int getNumber(){
        return this.number;
    }

    public long getBalance(){
        return this.balance;
    }

    /**
     * Deposits money into the account
     * @param amount The amount of money to deposit
     * @throws IllegalArgumentException If the amount of money to deposit is negative
     */
    public void deposit(long amount){
        if(amount < 0){
            throw new IllegalArgumentException("The amount must be a positive number");
        }

        balance += amount;
    }

    /**
     * Withdraws money from the account
     * @param amount The amount of money to withdraw
     * @throws IllegalArgumentException If the amount of money to withdraw is negative
     */
    public void withdraw(long amount){
        if(amount < 0){
            throw new IllegalArgumentException("The amount must be a positive number");
        }

        balance -= amount;
    }
}
