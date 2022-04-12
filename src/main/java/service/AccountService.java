package service;

import exception.InvalidAmountException;
import model.Account;

import java.math.BigDecimal;

public interface AccountService {
    void execute(Account account, BigDecimal amount) throws InvalidAmountException;

    default void displayHistory(Account account) {
        account.getHistory().forEach(transactionRecord -> System.out.println(transactionRecord.toString()));
    }
}
