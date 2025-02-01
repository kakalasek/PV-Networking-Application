package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

/**
 * Returns the code of the bank
 */
public class BankCodeCommand implements BankCommand {

    private final Bank bank;

    public BankCodeCommand(Bank bank){
        this.bank = bank;
    }

    /**
     * Used to obtain the code of the bank
     * @param args Does not expect any args
     * @return The code of the bank
     */
    @Override
    public String execute(String[] args) {
        return bank.getBankCode();
    }
}
