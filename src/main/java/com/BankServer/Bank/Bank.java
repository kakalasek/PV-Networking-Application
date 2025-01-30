package com.BankServer.Bank;

import com.BankServer.Bank.Account.Account;

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

    public String getBankCode(){
        return this.bankCode;
    }
}
