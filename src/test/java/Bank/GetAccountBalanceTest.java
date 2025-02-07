package Bank;

import com.BankServer.Bank.Bank;
import com.CustomExceptions.NonExistingAccountException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class GetAccountBalanceTest {

    @Test
    public void gettingAccountBalanceWorks() throws IOException {
        Bank bank = new Bank("10.10.10.10", 10_000, 20_000);
        int accountNumber = bank.createAccount();
        assertEquals(0, bank.getAccountBalance(accountNumber));
    }

    @Test
    public void gettingAccountBalanceFromANonExistentAccountThrowsAnException() throws IOException {
        Bank bank = new Bank("10.10.10.10", 10_000, 20_000);

        NonExistingAccountException nonExistingAccountException = assertThrows(NonExistingAccountException.class, ()->{
            bank.getAccountBalance(15000);
        });
    }
}
