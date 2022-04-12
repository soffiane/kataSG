package service.impl;

import exception.InvalidAmountException;
import model.Account;
import service.AccountService;

import java.math.BigDecimal;

public class DepositAccountServiceImpl implements AccountService {
    @Override
    public void execute(Account account, BigDecimal amount) throws InvalidAmountException {
        account.setBalance(account.getBalance().add(amount));
    }
}
