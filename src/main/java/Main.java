import exception.InvalidAmountException;
import model.Account;
import model.Operation;
import service.AccountService;
import service.TransactionService;
import service.impl.DepositAccountServiceImpl;
import service.impl.TransactionServiceImpl;
import service.impl.WithdrawalAccountServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Main {

    public static void main(String[] args) {
        AccountService depositAccountService = new DepositAccountServiceImpl();
        AccountService withdrawAccountService = new WithdrawalAccountServiceImpl();
        TransactionService transactionService = new TransactionServiceImpl();

        Account account = new Account(BigDecimal.valueOf(500.00));
        BigDecimal amount = BigDecimal.valueOf(152.63);
        try {
            depositAccountService.execute(account, amount);
            transactionService.recordTransaction(transactionService.createTransaction(Operation.DEPOSIT, LocalDateTime.now(), amount, account.getBalance()), account);
            amount = BigDecimal.valueOf(556.46);
            withdrawAccountService.execute(account, amount);
            transactionService.recordTransaction(transactionService.createTransaction(Operation.WITHDRAWAL, LocalDateTime.now(), amount, account.getBalance()), account);
            depositAccountService.displayHistory(account);
        } catch (InvalidAmountException e) {
            e.printStackTrace();
        }
    }
}
