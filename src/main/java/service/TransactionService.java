package service;

import model.Account;
import model.Operation;
import model.TransactionRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionService {
    void recordTransaction(TransactionRecord transactionRecord, Account account);

    TransactionRecord createTransaction(Operation operation, LocalDateTime dateTime, BigDecimal amount, BigDecimal balance);
}
