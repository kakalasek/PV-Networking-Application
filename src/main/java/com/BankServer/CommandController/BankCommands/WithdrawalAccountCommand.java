package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;
import com.Utils.InputHandler.InputHandler;
import com.google.common.net.InetAddresses;

import java.security.InvalidParameterException;

/**
 * Used to withdraw money from a bank account
 */
public class WithdrawalAccountCommand implements BankCommand {

    private final Bank bank;

    public WithdrawalAccountCommand(Bank bank){
        this.bank = bank;
    }

    /**
     * Withdraws money from a bank account
     * @param args args[0] ... accountNumber/bankCode(ipv4 address)
     *             args[1] ... amount of money to withdraw (in long datatype)
     * @return If the withdrawal is successful, returns an empty string
     * @throws InvalidParameterException If there is any problem with the provided arguments
     */
    @Override
    public String execute(String[] args) {
        String[] accountAndIp = args[0].split("/");

        if(accountAndIp.length != 2){
            throw new InvalidParameterException("You have to provide your account number and ip separated by /");
        }

        String account = accountAndIp[0];
        String ip = accountAndIp[1];
        String withdrawal = args[1];

        if(!account.matches("\\d+")) throw new InvalidParameterException("The account number must be a natural number");
        if(!bank.isAccountNumber(Integer.parseInt(account))) throw new InvalidParameterException("This account number cant exist in this bank");
        if(!InetAddresses.isInetAddress(ip)) throw new InvalidParameterException("The bank code must be a valid ip address");
        if(!withdrawal.matches("\\d+")) throw new InvalidParameterException("The withdrawal must be a natural number");
        if(!InputHandler.isValidLong(withdrawal)) throw new InvalidParameterException("The amount of money you want to withdraw is too large");

        bank.withdrawMoney(Integer.parseInt(account), Integer.parseInt(withdrawal));

        return "";
    }
}
