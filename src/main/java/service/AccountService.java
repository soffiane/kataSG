package service;

import exception.InvalidAmountException;
import model.Account;
import model.TransactionRecord;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface AccountService {
    TransactionRecord executeTransaction(Account account, BigDecimal amount, LocalDateTime date) throws InvalidAmountException;
}
