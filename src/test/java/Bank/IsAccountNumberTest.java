package Bank;

import com.BankServer.Bank.Bank;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class IsAccountNumberTest {

    @Test
    public void correctlyIdentifiesAValidAccountNumber() throws IOException {
        Bank bank = new Bank("10.10.10.10", 10_000, 20_000);
        assertTrue(bank.isAccountNumber(15_000));
    }

    @Test
    public void correctlyIdentifiesAnInvalidAccountNumber() throws IOException {
        Bank bank = new Bank("10.10.10.10", 10_000, 20_000);
        assertFalse(bank.isAccountNumber(40_000));
    }
}
