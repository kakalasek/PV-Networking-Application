package com.BankServer.CommandController.BankCommands;

import com.BankServer.Bank.Bank;
import com.Utils.InputHandler.InputHandler;
import com.google.common.net.InetAddresses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.security.InvalidParameterException;

/**
 * Used to withdraw money from a bank account
 */
public class WithdrawalAccountCommand implements BankCommand {

    private final Bank bank;
    private final int port;

    public WithdrawalAccountCommand(Bank bank, int port){
        this.bank = bank;
        this.port = port;
    }

    /**
     * Withdraws money from a bank account. If a different bank id is provided, it will attempt to perform it at that bank
     * @param args args[0] ... the command code
     *             args[1] ... accountNumber/bankCode(ipv4 address)
     *             args[2] ... amount of money to withdraw (in long datatype)
     * @return If the withdrawal is successful, returns an empty string
     * @throws InvalidParameterException If there is any problem with the provided arguments
     * @throws RuntimeException If the provided ip does not correspond to any bank, or the server responded for too long
     */
    @Override
    public String execute(String[] args) {
        if(args.length <= 2) throw new InvalidParameterException("You have to provide your account number and ip separated by / and the amount to withdraw");

        String[] accountAndIp = args[1].split("/");

        if(accountAndIp.length != 2) throw new InvalidParameterException("You have to provide your account number and ip separated by /");

        String account = accountAndIp[0];
        String ip = accountAndIp[1];
        String withdrawal = args[2];

        if(!account.matches("\\d+")) throw new InvalidParameterException("The account number must be a natural number");
        if(!bank.isAccountNumber(Integer.parseInt(account))) throw new InvalidParameterException("This account number cant exist in this bank");
        if(!InetAddresses.isInetAddress(ip)) throw new InvalidParameterException("The bank code must be a valid ip address");
        if(!withdrawal.matches("\\d+")) throw new InvalidParameterException("The withdrawal must be a natural number");
        if(!InputHandler.isValidLong(withdrawal)) throw new InvalidParameterException("The amount of money you want to withdraw is too large");

        if(ip.equals(bank.getBankCode())){
            bank.withdrawMoney(Integer.parseInt(account), Integer.parseInt(withdrawal));
            return args[0];
        }

        try (Socket socket = new Socket(InetAddresses.forString(ip), port)){
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.println("AW " + account + "/" + ip + " " + withdrawal);

            return in.readLine();

        } catch (IOException e) {
            throw new RuntimeException("Provided ip either does not belong to any server, or the server did not respond in time");
        }
    }
}
