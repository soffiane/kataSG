package model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Account {

    private BigDecimal balance;
    private List<TransactionRecord> history;

    public Account(BigDecimal balance) {
        this.balance = balance;
        history = new ArrayList<>();
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public List<TransactionRecord> getHistory() {
        return history;
    }

    public void deposit(BigDecimal amount) {
        setBalance(this.balance.add(amount));
    }

    public void withdraw(BigDecimal amount) {
        setBalance(this.balance.subtract(amount));
    }
}
