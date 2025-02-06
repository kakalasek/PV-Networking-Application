package Account;

import com.BankServer.Bank.Account.Account;
import com.CustomExceptions.AccountOverflowException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class WithdrawMoneyTest {

    @Test
    public void withdrawMoneyActuallyWithdrawsMoney(){
        Account account = new Account(0xA7, 20000);

        account.withdraw(10000);

        assertEquals(10000, account.getBalance());
    }

    @Test
    public void withdrawNegativeNumberOfThrowsAnException(){
        Account account = new Account(075, 20000);


        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, ()->{
            account.withdraw(-15000);
        });
    }

    @Test
    public void withdrawTooMuchMoneyThrowsAnException(){
        Account account = new Account(0b1001101, Long.MIN_VALUE);

        AccountOverflowException accountOverflowException = assertThrows(AccountOverflowException.class, () -> {
            account.withdraw(10);
        });

    }
}
