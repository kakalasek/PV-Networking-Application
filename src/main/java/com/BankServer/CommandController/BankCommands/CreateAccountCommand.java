package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class CreateAccountCommand implements BankCommand {

    Bank bank;

    public CreateAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        return bank.createAccount();
    }
}
