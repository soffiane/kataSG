package model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TransactionRecord {
    private Operation operation;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private BigDecimal balance;

    public TransactionRecord(Operation operation, LocalDateTime dateTime, BigDecimal amount, BigDecimal balance) {
        this.operation = operation;
        this.amount = amount;
        this.dateTime = dateTime;
        this.balance = balance;
    }

    public Operation getOperation() {
        return operation;
    }

    public void setOperation(Operation operation) {
        this.operation = operation;
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

    @Override
    public String toString() {
        return "TransactionRecord{" +
                "operation=" + operation +
                ", amount=" + amount +
                ", dateTime=" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(dateTime) +
                ", balance=" + balance +
                '}';
    }
}
