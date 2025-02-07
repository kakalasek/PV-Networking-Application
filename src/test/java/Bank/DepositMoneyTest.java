package Bank;

import com.BankServer.Bank.Bank;
import com.CustomExceptions.NonExistingAccountException;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class DepositMoneyTest {

    @Test
    public void depositingMoneyActuallyDepositsMoney() throws IOException {
        Bank bank = new Bank("10.20.10.20", 10_000, 20_000);
        int accountNumber = bank.createAccount();
        bank.depositMoney(accountNumber, 30_000);
        assertEquals(30_000, bank.getAccountBalance(10_000));
    }

    @Test
    public void depositingMoneyToANonexistentAccountThrowsAnException() throws IOException{
        Bank bank = new Bank("10.20.20.10", 10_000, 10_100);

        NonExistingAccountException nonExistingAccountException = assertThrows(NonExistingAccountException.class, ()->{
            bank.depositMoney(10_003, 69000);
        });
    }
}
