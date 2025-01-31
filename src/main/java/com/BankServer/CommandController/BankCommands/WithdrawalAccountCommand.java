package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class WithdrawalAccountCommand implements BankCommand {

    private Bank bank;

    public WithdrawalAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        return "";
    }
}
