package com.BankServer.Bank;

import com.BankServer.Bank.Account.Account;
import com.CustomExceptions.AccountDebtException;
import com.CustomExceptions.AccountNotEmptyException;
import com.CustomExceptions.AccountsFullException;
import com.CustomExceptions.NonExistingAccountException;
import com.Utils.FileHandler.FileHandler;
import com.CustomExceptions.AccountOverflowException;

import java.io.IOException;
import java.math.BigInteger;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Bank {

    private final String bankCode;
    private final AbstractMap<Integer, Account> accountsMap;
    private int currentAccountNumber;
    private final int minAccountNumber;
    private final int maxAccountNumber;
    private final List<Integer> availableAccountNumbersOutOfOrder;
    private final String accountsFilePath;
    private final String outOfOrderFilePath;
    private BigInteger bankTotal;

    /**
     * Creates the bank object
     * @param bankCode Represents the code of the bank
     * @throws IOException Is thrown if creating the compulsory files fails
     */
    public Bank(String bankCode, int minAccountNumber, int maxAccountNumber) throws IOException {
        this.bankCode = bankCode;
        this.accountsMap = new HashMap<>();
        this.availableAccountNumbersOutOfOrder = new ArrayList<>();
        this.minAccountNumber = minAccountNumber;
        this.maxAccountNumber = maxAccountNumber;
        this.currentAccountNumber = this.minAccountNumber;
        this.accountsFilePath = "accounts.csv";
        this.outOfOrderFilePath = "outoforder.csv";
        this.bankTotal = BigInteger.ZERO;
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
        if (accountsMap.size() >= maxAccountNumber){
            throw new AccountsFullException("Cant create any more accounts in this bank");
        }

        Account newAccount;

            if(!availableAccountNumbersOutOfOrder.isEmpty()){
                newAccount = new Account(availableAccountNumbersOutOfOrder.removeFirst(), 0);
            } else{
                newAccount = new Account(currentAccountNumber++, 0);
            }

        accountsMap.put(newAccount.getNumber(), newAccount);
        return newAccount.getNumber();
    }

    /**
     * Removes an account. It also adds its number into availableAccountNumbersOutOfOrder, so it can be reused
     * with the next account creation
     * @param accountNumber Number of the account you want to deposit money to
     * @throws NonExistingAccountException If the supplied account number does not correspond to any account in the bank
     * @throws AccountNotEmptyException If the accounts balance is not zero
     */
    public void removeAccount(int accountNumber){
        Account account = accountsMap.get(accountNumber);

        if(account == null) throw new NonExistingAccountException("Account with this number does not exist");

        if(account.getBalance() > 0) throw new AccountNotEmptyException("You can only delete an account with balance equal to zero");

        accountsMap.remove(accountNumber);

        availableAccountNumbersOutOfOrder.add(accountNumber);
    }

    public String getBankCode(){
        return this.bankCode;
    }

    public int getNumberOfClients(){
        return accountsMap.size();
    }

    public BigInteger getBankTotal(){
        return bankTotal;
    }

    /**
     * Deposits money into an account
     * @param accountNumber Number of the account you want to deposit money to
     * @param deposit The amount of money you want to deposit
     * @throws NonExistingAccountException If the supplied account number does not correspond to any account in the bank
     * @throws IllegalArgumentException If the amount of money to deposit is negative
     * @throws AccountOverflowException If the amount of money to deposit would overflow the accounts balance
     */
    public void depositMoney(int accountNumber, long deposit){
        Account account = accountsMap.get(accountNumber);

        if(account == null) throw new NonExistingAccountException("Account with this number does not exist");

        account.deposit(deposit);
        bankTotal = bankTotal.add(BigInteger.valueOf(deposit));
    }

    /**
     * Returns the balance of the specified account
     * @param accountNumber Number of the account you want check the balance of
     * @return The balance of the account
     * @throws NonExistingAccountException If the supplied account number does not correspond to any account in the bank
     */
    public long getAccountBalance(int accountNumber){
        Account account = accountsMap.get(accountNumber);

        if(account == null) throw new NonExistingAccountException("Account with this number does not exist");

        return account.getBalance();
    }

    /**
     * Withdraws money from the account
     * @param accountNumber Number of the account you want to withdraw money from
     * @param withdrawal The amount of money you want to withdraw
     * @throws NonExistingAccountException If the supplied account number does not correspond to any account in the bank
     * @throws AccountDebtException If the withdrawal was to result in negative balance on the account
     * @throws IllegalArgumentException If the amount of money to withdraw is negative
     * @throws AccountOverflowException If the amount of money to withdraw would overflow the accounts balance
     */
    public void withdrawMoney(int accountNumber, long withdrawal){
        Account account = accountsMap.get(accountNumber);

        if(account == null) throw new NonExistingAccountException("Account with this number does not exist");

        if((account.getBalance() - withdrawal) < 0) throw new AccountDebtException("You cannot withdraw more money than you have on your account");

        account.withdraw(withdrawal);
        bankTotal = bankTotal.subtract(BigInteger.valueOf(withdrawal));
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

        for(Account account : accountsMap.values()){
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

            accountsMap.put(number, new Account(number, balance));
        }
        this.currentAccountNumber = highestAccountNumber;

        for(String[] accountNumber : FileHandler.readCsv(outOfOrderFilePath)){
            availableAccountNumbersOutOfOrder.add(Integer.valueOf(accountNumber[0]));
        }
    }
}
