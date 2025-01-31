package com.BankServer.Bank;

import com.BankServer.Bank.Account.Account;
import com.Utils.FileHandler.FileHandler;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Bank {

    private final String bankCode;
    private final List<Account> accounts;
    private int currentAccountNumber = 10000;

    public Bank(String bankCode){
        this.bankCode = bankCode;
        accounts = new ArrayList<>();
    }

    // TODO create a mechanism for creating accounts which numbers were already assigned
    public int createAccount(){
        if (accounts.size() == 90_000){
            throw new RuntimeException("Cant create any more accounts");
        }

        Account newAccount = new Account(currentAccountNumber++, 0);
        accounts.add(newAccount);
        return newAccount.getNumber();
    }

    public void removeAccount(int number){
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getNumber() == number){
                accounts.remove(i);
                return;
            }
        }

        throw new RuntimeException("Account with this number does not exist");
    }

    public String getBankCode(){
        return this.bankCode;
    }

    public int getNumberOfClients(){
        return accounts.size();
    }

    public BigInteger getBankTotal(){
        BigInteger bankTotal = BigInteger.ZERO;
        for(Account account : accounts){
            bankTotal = bankTotal.add(BigInteger.valueOf(account.getBalance()));
        }

        return bankTotal;
    }

    public void depositMoney(int accountNumber, long deposit){
        for(Account account : accounts){
            if(account.getNumber() == accountNumber){
                account.deposit(deposit);
                return;
            }
        }

        throw new RuntimeException("Account with this number does not exist");
    }

    public long getAccountBalance(int accountNumber){
        for(Account account : accounts){
            if(account.getNumber() == accountNumber){
                return account.getBalance();
            }
        }

        throw new RuntimeException("Account with this number does not exist");
    }

    public void withdrawMoney(int accountNumber, long withdrawal){
        for(Account account : accounts){
            if(account.getNumber() == accountNumber){
                account.withdraw(withdrawal);
                return;
            }
        }

        throw new RuntimeException("Account with this number does not exist");
    }

    public void saveAccounts() throws IOException {
        FileHandler.clearFile("accounts.csv");

        for(Account account : accounts){
            String number = String.valueOf(account.getNumber());
            String balance = String.valueOf(account.getBalance());

            FileHandler.appendCsvRow("accounts.csv", new String[]{number, balance});
        }
    }

    public void readAccounts() throws IOException{
        for(String[] account : FileHandler.readCsv("accounts.csv")){
            int number = Integer.parseInt(account[0]);
            int balance = Integer.parseInt(account[1]);

            accounts.add(new Account(number, balance));
        }
    }
}
