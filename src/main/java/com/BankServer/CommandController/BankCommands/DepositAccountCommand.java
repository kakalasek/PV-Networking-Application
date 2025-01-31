package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class DepositAccountCommand implements BankCommand{

    private final Bank bank;

    public DepositAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        String account = args[0].split("/")[0];
        String ip = args[0].split("/")[1];
        String deposit = args[1];

        bank.depositMoney(Integer.parseInt(account), Integer.parseInt(deposit));

        return "";
    }
}
