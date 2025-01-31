package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class RemoveAccountCommand implements BankCommand{

    private Bank bank;

    public RemoveAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        return "";
    }
}
