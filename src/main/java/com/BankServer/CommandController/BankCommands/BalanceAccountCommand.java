package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class BalanceAccountCommand implements BankCommand {

    private Bank bank;

    public BalanceAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        return "";
    }
}
