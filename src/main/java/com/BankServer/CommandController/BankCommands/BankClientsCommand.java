package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

/**
 * Used to get the number clients
 */
public class BankClientsCommand implements BankCommand{

    private final Bank bank;

    public BankClientsCommand(Bank bank){
        this.bank = bank;
    }

    /**
     * @param args Does not expect any arguments
     * @return The number of clients in the bank
     */
    @Override
    public String execute(String[] args) {
        return String.valueOf(bank.getNumberOfClients());
    }
}
