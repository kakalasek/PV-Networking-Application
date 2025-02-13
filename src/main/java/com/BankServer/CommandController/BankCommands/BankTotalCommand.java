package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;

/**
 * Used to obtain the total amount of money in the bank
 */
public class BankTotalCommand implements BankCommand{

    private final Bank bank;

    public BankTotalCommand(Bank bank){
        this.bank = bank;
    }

    /**
     * @param args  args[0] ... the command code
     * @return The total number of money in the bank (sum of money on all the accounts)
     */
    @Override
    public String execute(String[] args) {
        return args[0] + " " + bank.getBankTotal().toString();
    }
}
