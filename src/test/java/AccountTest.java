import exception.InvalidAmountException;
import model.Account;
import model.Operation;
import model.TransactionRecord;
import org.junit.Before;
import org.junit.Test;
import service.AccountService;
import service.TransactionService;
import service.impl.DepositAccountServiceImpl;
import service.impl.TransactionServiceImpl;
import service.impl.WithdrawalAccountServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private AccountService depositAccountService;
    private AccountService withdrawalAccountService;
    private TransactionService transactionService;

    @Before
    public void setup() {
        depositAccountService = new DepositAccountServiceImpl();
        withdrawalAccountService = new WithdrawalAccountServiceImpl();
        transactionService = new TransactionServiceImpl();
    }

    @Test
    public void shouldDepositAmountOnAccount() throws InvalidAmountException {
        Account account = new Account(BigDecimal.valueOf(150.86));
        depositAccountService.execute(account, BigDecimal.valueOf(200));
        assertEquals(BigDecimal.valueOf(350.86), account.getBalance());
    }

    @Test
    public void shouldWithdrawAmountFromAccount() throws InvalidAmountException {
        Account account = new Account(BigDecimal.valueOf(150.86));
        withdrawalAccountService.execute(account, BigDecimal.valueOf(100));
        assertEquals(BigDecimal.valueOf(50.86), account.getBalance());
    }

    @Test(expected = InvalidAmountException.class)
    public void shouldThrowExceptionWhenNotEnoughMoney() throws InvalidAmountException {
        Account account = new Account(BigDecimal.valueOf(150.86));
        withdrawalAccountService.execute(account, BigDecimal.valueOf(200));
    }

    @Test
    public void shouldDisplayHistory() throws InvalidAmountException {
        Account account = new Account(BigDecimal.valueOf(150.86));
        BigDecimal amount = BigDecimal.valueOf(200);
        depositAccountService.execute(account, amount);
        TransactionRecord transaction = transactionService.createTransaction(Operation.DEPOSIT, LocalDateTime.now(), amount, account.getBalance());
        transactionService.recordTransaction(transaction, account);
        withdrawalAccountService.execute(account, BigDecimal.valueOf(100));
        TransactionRecord secondTransaction = transactionService.createTransaction(Operation.WITHDRAWAL, LocalDateTime.now(), amount, account.getBalance());
        transactionService.recordTransaction(secondTransaction, account);
        assertEquals(account.getHistory().size(), 2);
        assertEquals(account.getHistory().get(0).getOperation(), Operation.DEPOSIT);
    }

}
