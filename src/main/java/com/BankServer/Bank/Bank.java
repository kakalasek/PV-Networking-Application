package com.BankServer.Bank;

import com.BankServer.Bank.Account.Account;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final String bankCode;
    private final List<Account> accounts;
    private int currentAccountNumber = 10000;

    public Bank(String bankCode){
        this.bankCode = bankCode;
        accounts = new ArrayList<>();
    }

    // TODO create a mechanism for creating accounts which numbers were already assigned
    public String createAccount(){
        if (accounts.size() == 90_000){
            return "Cant create any more accounts";
        }

        Account newAccount = new Account(currentAccountNumber++, 0);
        accounts.add(newAccount);
        return newAccount.getNumber() + "/" + bankCode;
    }

    public String removeAccount(int number){
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getNumber() == number){
                accounts.remove(i);
                return String.valueOf(number);
            }
        }

        return "Account with this number does not exist";
    }

    public String getBankCode(){
        return this.bankCode;
    }

    public String getNumberOfClients(){
        return String.valueOf(accounts.size());
    }

    public String getBankTotal(){
        BigInteger bankTotal = BigInteger.ZERO;
        for(Account account : accounts){
            bankTotal = bankTotal.add(BigInteger.valueOf(account.getBalance()));
        }

        return bankTotal.toString();
    }

}
