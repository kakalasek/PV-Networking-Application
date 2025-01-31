package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class BankClientsCommand implements BankCommand{

    private Bank bank;

    public BankClientsCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        return "";
    }
}
