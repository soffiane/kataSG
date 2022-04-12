package service.impl;

import exception.InvalidAmountException;
import model.Account;
import service.AccountService;

import java.math.BigDecimal;

public class WithdrawalAccountServiceImpl implements AccountService {
    @Override
    public void execute(Account account, BigDecimal amount) throws InvalidAmountException {
        if (account.getBalance().compareTo(amount) < 0) throw new InvalidAmountException("Unsufficent funds");
        account.setBalance(account.getBalance().subtract(amount));
    }
}
