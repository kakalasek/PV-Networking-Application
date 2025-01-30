package com.BankServer.Bank.Account;

public class Account {
    private int number;
    private int balance;

    public Account(int number, int balance){
        this.number = number;
        this.balance = balance;
    }

    public int getNumber(){
        return this.number;
    }

    public int getBalance(){
        return this.balance;
    }
}
