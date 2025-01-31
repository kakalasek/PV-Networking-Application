package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class BankCodeCommand implements BankCommand {

    Bank bank;

    public BankCodeCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        System.out.println(bank.getBankCode());
        return bank.getBankCode();
    }
}
