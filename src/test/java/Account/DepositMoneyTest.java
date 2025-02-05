package Account;

import com.BankServer.Bank.Account.Account;
import com.CustomExceptions.AccountOverflowException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DepositMoneyTest {

    @Test
    public void depositMoneyActuallyDepositsMoney(){
        Account account = new Account(69, 0);

        account.deposit(10000);

        assertEquals(10000, account.getBalance());
    }

    @Test
    public void depositNegativeNumberThrowsAnException(){
        Account account = new Account(666, 0);

        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, () -> {
            account.deposit(-2000);
        });
    }

    @Test
    public void depositTooMuchMoneyThrowsAnException(){
        Account account = new Account(440, 999999999999999999L);

        AccountOverflowException accountOverflowException = assertThrows(AccountOverflowException.class, () -> {
            account.deposit(Long.MAX_VALUE);
        });
    }
}
