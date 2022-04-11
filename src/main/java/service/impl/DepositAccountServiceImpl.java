package service.impl;

import exception.InvalidAmountException;
import model.Account;
import model.TransactionRecord;
import service.AccountService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class DepositAccountServiceImpl implements AccountService {
    @Override
    public TransactionRecord executeTransaction(Account account, BigDecimal amount, LocalDateTime date) throws InvalidAmountException {
        account.deposit(amount);
        return new TransactionRecord(date,amount,account.getBalance());
    }
}
