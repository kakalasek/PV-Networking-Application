package Bank;

import com.BankServer.Bank.Bank;
import com.CustomExceptions.AccountDebtException;
import com.CustomExceptions.NonExistingAccountException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class WithdrawMoneyTest {

    @Test
    public void withdrawMoneyActuallyWithdrawsMoney() throws IOException {
        Bank bank = new Bank("10.20.10.20", 10_000, 20_000);
        int accountNumber = bank.createAccount();
        bank.depositMoney(accountNumber, 30_000);
        bank.withdrawMoney(accountNumber, 15_000);
        assertEquals(15_000, bank.getAccountBalance(10_000));

    }

    @Test
    public void withdrawingMoneyFromANonExistentAccountThrowsAnException() throws IOException {
        Bank bank = new Bank("10.20.10.20", 10_000, 20_000);

        NonExistingAccountException nonExistingAccountException = assertThrows(NonExistingAccountException.class, ()->{
            bank.withdrawMoney(10_003, 69000);
        });
    }

    @Test
    public void withdrawingMoreMoneyThanThereIsOnTheAccountThrowsAnException() throws IOException {
        Bank bank = new Bank("10.20.10.20", 10_000, 20_000);
        int accountNumber = bank.createAccount();
        bank.depositMoney(accountNumber, 30_000);

        AccountDebtException accountDebtException = assertThrows(AccountDebtException.class, ()->{
            bank.withdrawMoney(accountNumber, 69000);
        });
    }
}
