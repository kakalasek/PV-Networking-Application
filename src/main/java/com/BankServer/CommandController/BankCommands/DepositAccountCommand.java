package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class DepositAccountCommand implements BankCommand{

    private Bank bank;

    public DepositAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        return "";
    }
}
