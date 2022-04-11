package service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public interface TransactionService {
    void recordTransaction(LocalDateTime date, BigDecimal amount, LocalDateTime dateTime);
}
