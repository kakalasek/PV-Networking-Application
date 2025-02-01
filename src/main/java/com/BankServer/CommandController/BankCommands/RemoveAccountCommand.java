package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;
import com.google.common.net.InetAddresses;

import java.security.InvalidParameterException;

/**
 * Used to remove an existing account
 */
public class RemoveAccountCommand implements BankCommand{

    private final Bank bank;

    public RemoveAccountCommand(Bank bank){
        this.bank = bank;
    }

    /**
     * Removes an existing account
     * @param args args[0] ... accountNumber/bankCode(ipv4 address)
     * @return If the removal executes without any problem, returns an empty string
     */
    @Override
    public String execute(String[] args) {
        String[] accountAndIp = args[0].split("/");

        if(accountAndIp.length != 2){
            throw new InvalidParameterException("You have to provide your account number and ip separated by /");
        }

        String account = accountAndIp[0];
        String ip = accountAndIp[1];

        if(!account.matches("\\d+")) throw new InvalidParameterException("The account number must be a natural number");
        if(!bank.isAccountNumber(Integer.parseInt(account))) throw new InvalidParameterException("This account number cant exist in this bank");
        if(!InetAddresses.isInetAddress(ip)) throw new InvalidParameterException("The bank code must be a valid ip address");

        bank.removeAccount(Integer.parseInt(account));

        return "";
    }
}
