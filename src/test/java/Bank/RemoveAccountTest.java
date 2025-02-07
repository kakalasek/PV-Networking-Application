package Bank;

import com.BankServer.Bank.Bank;
import com.CustomExceptions.AccountNotEmptyException;
import com.CustomExceptions.NonExistingAccountException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class RemoveAccountTest {

    @Test
    public void removingAnAccountActuallyWorks() throws IOException {
        Bank bank = new Bank("10.10.10.10", 10_000, 20_000);
        int accountNumber = bank.createAccount();
        bank.removeAccount(accountNumber);
        assertEquals(0, bank.getNumberOfClients());
    }

    @Test
    public void removingANonExistentAccountThrowsAnException() throws IOException{
        Bank bank = new Bank("10.10.10.10", 10_000, 20_000);

        NonExistingAccountException nonExistingAccountException = assertThrows(NonExistingAccountException.class, ()->{
            bank.removeAccount(10_000);
        });
    }

    @Test
    public void removingAnAccountWithBalanceThrowsAnException() throws IOException{
        Bank bank = new Bank("10.10.10.10", 10_000, 20_000);
        int accountNumber = bank.createAccount();
        bank.depositMoney(accountNumber, 10_000);

        AccountNotEmptyException accountNotEmptyException = assertThrows(AccountNotEmptyException.class, ()->{
            bank.removeAccount(accountNumber);
        });
    }
}
