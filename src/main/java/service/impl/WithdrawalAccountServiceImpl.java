package service.impl;

import exception.InvalidAmountException;
import model.Account;
import model.TransactionRecord;
import service.AccountService;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class WithdrawalAccountServiceImpl implements AccountService {
    @Override
    public TransactionRecord executeTransaction(Account account, BigDecimal amount, LocalDateTime date) throws InvalidAmountException {
       if(account.getBalance().compareTo(amount) < 0) throw new InvalidAmountException("Not enough money on your account");
       account.withdraw(amount);
       return new TransactionRecord(date,amount,account.getBalance());
    }
}
