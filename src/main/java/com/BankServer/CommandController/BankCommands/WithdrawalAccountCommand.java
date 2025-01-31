package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

public class WithdrawalAccountCommand implements BankCommand {

    private final Bank bank;

    public WithdrawalAccountCommand(Bank bank){
        this.bank = bank;
    }

    @Override
    public String execute(String[] args) {
        String account = args[0].split("/")[0];
        String ip = args[0].split("/")[1];
        String withdrawal = args[1];

        bank.withdrawMoney(Integer.parseInt(account), Integer.parseInt(withdrawal));

        return "";
    }
}
