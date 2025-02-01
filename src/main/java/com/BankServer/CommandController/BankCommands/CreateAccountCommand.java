package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

/**
 * Used to create a new account
 */
public class CreateAccountCommand implements BankCommand {

    private final Bank bank;

    public CreateAccountCommand(Bank bank){
        this.bank = bank;
    }

    /**
     * Creates a new account
     * @param args Does not expect any arguments
     * @return The number of the newly created account and the code of the bank
     */
    @Override
    public String execute(String[] args) {

        return bank.createAccount() + "/" + bank.getBankCode();
    }
}
