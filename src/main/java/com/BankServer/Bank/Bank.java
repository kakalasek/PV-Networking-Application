package com.BankServer.Bank;

import com.BankServer.Bank.Account.Account;
import com.CustomExceptions.AccountsFullException;
import com.Utils.FileHandler.FileHandler;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents a bank.
 */
public class Bank {

    private final String bankCode;
    private final List<Account> accounts;
    private int currentAccountNumber;
    private final int minAccountNumber;
    private final int maxAccountNumber;
    private final List<Integer> availableAccountNumbersOutOfOrder;
    private final String accountsFilePath;
    private final String outOfOrderFilePath;

    /**
     * Creates the bank object
     * @param bankCode Represents the code of the bank
     * @throws IOException Is thrown if creating the compulsory files fails
     */
    public Bank(String bankCode) throws IOException {
        this.bankCode = bankCode;
        this.accounts = new ArrayList<>();
        this.availableAccountNumbersOutOfOrder = new ArrayList<>();
        this.minAccountNumber = 10_000;
        this.maxAccountNumber = 99_999;
        this.currentAccountNumber = this.minAccountNumber;
        this.accountsFilePath = "accounts.csv";
        this.outOfOrderFilePath = "outoforder.csv";
        FileHandler.createFiles(new String[]{accountsFilePath, outOfOrderFilePath});
    }

    /**
     * Creates a new account. If there are any account numbers out of sequence, it will use those numbers
     * preferably, otherwise it will continue sequentially from currentAccountNumber (current value of
     * currentAccountNumber will be assigned to the next Account and incremented)
     * @return The number of the created account
     * @throws AccountsFullException Gets thrown if this bank already has all possible accounts created
     */
    public int createAccount(){
        if (accounts.size() >= maxAccountNumber){
            throw new AccountsFullException("Cant create any more accounts in this bank");
        }

        Account newAccount;

            if(!availableAccountNumbersOutOfOrder.isEmpty()){
                newAccount = new Account(availableAccountNumbersOutOfOrder.removeFirst(), 0);
            } else{
                newAccount = new Account(currentAccountNumber++, 0);
            }

        accounts.add(newAccount);
        return newAccount.getNumber();
    }

    public void removeAccount(int number){
        for(int i = 0; i < accounts.size(); i++){
            if(accounts.get(i).getNumber() == number){
                availableAccountNumbersOutOfOrder.add(accounts.remove(i).getNumber());
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

    /**
     * Checks if the provided account number can be an account number in this bank
     * @param accountNumber The account number you want to test
     * @return True if the account number can be an account number in this bank, false if not
     */
    public boolean isAccountNumber(int accountNumber){
        return accountNumber >= minAccountNumber && accountNumber <= maxAccountNumber;
    }

    /**
     * Saves accounts into a csv file. It also saves the out-of-order account numbers.
     * @throws IOException Gets thrown if there is any problem with writing the accounts into the csv file
     */
    public void saveAccounts() throws IOException {
        FileHandler.clearFile(accountsFilePath);
        FileHandler.clearFile(outOfOrderFilePath);

        for(Account account : accounts){
            String number = String.valueOf(account.getNumber());
            String balance = String.valueOf(account.getBalance());

            FileHandler.appendCsvRow(accountsFilePath, new String[]{number, balance});
        }

        for(Integer accountNumber : availableAccountNumbersOutOfOrder){
            FileHandler.appendCsvRow(outOfOrderFilePath, new String[]{String.valueOf(accountNumber)});
        }
    }

    /**
     * It reads all the account numbers from a csv file. It also reads all the out-of-order account numbers
     * @throws IOException Gets thrown if something goes wrong while reading the csv files
     */
    public void readAccounts() throws IOException{
        int highestAccountNumber = 10000;

        for(String[] account : FileHandler.readCsv(accountsFilePath)){
            int number = Integer.parseInt(account[0]);
            int balance = Integer.parseInt(account[1]);

            if(number > highestAccountNumber) highestAccountNumber = number;

            accounts.add(new Account(number, balance));
        }
        this.currentAccountNumber = highestAccountNumber;

        for(String[] accountNumber : FileHandler.readCsv(outOfOrderFilePath)){
            availableAccountNumbersOutOfOrder.add(Integer.valueOf(accountNumber[0]));
        }
    }
}
