package com.BankServer.Bank.Account;

public class Account {
    private int number;
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

    public void deposit(long amount){
        balance += amount;
    }

    public void withdraw(long amount){
        balance -= amount;
    }
}
