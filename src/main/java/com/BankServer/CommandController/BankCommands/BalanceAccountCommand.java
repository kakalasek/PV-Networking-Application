package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class BalanceAccountCommand implements BankCommand {

    private final Bank bank;

    public BalanceAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        String account = args[0].split("/")[0];
        String ip = args[0].split("/")[1];

        return String.valueOf(bank.getAccountBalance(Integer.parseInt(account)));
    }
}
