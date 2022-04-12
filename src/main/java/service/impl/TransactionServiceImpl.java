package service.impl;

import model.Account;
import model.Operation;
import model.TransactionRecord;
import service.TransactionService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionServiceImpl implements TransactionService {
    @Override
    public void recordTransaction(TransactionRecord transactionRecord, Account account) {
        account.updateHistory(transactionRecord);
    }

    @Override
    public TransactionRecord createTransaction(Operation operation, LocalDateTime dateTime, BigDecimal amount, BigDecimal balance) {
        return new TransactionRecord(operation, dateTime, amount, balance);
    }
}
