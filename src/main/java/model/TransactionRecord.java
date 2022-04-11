package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransactionRecord {
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private BigDecimal balance;

    public TransactionRecord(LocalDateTime dateTime, BigDecimal amount, BigDecimal balance) {
        this.amount = amount;
        this.dateTime = dateTime;
        this.balance = balance;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

}
