package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class RemoveAccountCommand implements BankCommand{

    private final Bank bank;

    public RemoveAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        String account = args[0].split("/")[0];
        String ip = args[0].split("/")[1];

        bank.removeAccount(Integer.parseInt(account));

        return "";
    }
}
