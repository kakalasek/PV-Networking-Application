package Bank;

import com.BankServer.Bank.Bank;
import com.CustomExceptions.AccountsFullException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class CreateAccountTest {

    @Test
    public void creatingAnAccountWorks() throws IOException {
        Bank bank = new Bank("110.10.10.10", 10_000, 99_999);
        bank.createAccount();

        assertEquals(1, bank.getNumberOfClients());
    }

    @Test
    public void creatingTooManyAccountsThrowsAnException() throws IOException {
        Bank bank = new Bank("192.168.0.1", 0, 10);
        for(int i = 0; i < 10; i++){
            bank.createAccount();
        }

        AccountsFullException accountsFullException = assertThrows(AccountsFullException.class, bank::createAccount);
    }
}
