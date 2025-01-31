package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class BankTotalCommand implements BankCommand{

    private Bank bank;

    public BankTotalCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        return "";
    }
}
