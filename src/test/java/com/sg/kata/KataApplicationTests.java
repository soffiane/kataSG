package com.sg.kata;

import com.sg.kata.entity.BankAccount;
import com.sg.kata.entity.Operation;
import com.sg.kata.enums.OperationType;
import com.sg.kata.service.BankAccountService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.Collections;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class KataApplicationTests {

    @Autowired
    private BankAccountService bankAccountService;

    @Test
    void contextLoads() {
    }

    @Test
    @Order(1)
    void testCreateBankAccount() {
        BankAccount bankAccount = bankAccountService.createBankAccount(new BankAccount());
        Assertions.assertEquals(1, bankAccount.getAccountNumber());
        Assertions.assertEquals(0.0, bankAccount.getBalance());
        Assertions.assertEquals(Collections.emptyList(), bankAccount.getOperationsList());
    }

    @Test
    @Order(2)
    void testDepositOnBankAccount() {
        BankAccount bankAccount = bankAccountService.createBankAccount(new BankAccount());
        BankAccount deposit = bankAccountService.deposit(bankAccount.getAccountNumber(), 50.0);
        Assertions.assertEquals(2, deposit.getAccountNumber());
        Assertions.assertEquals(50.0, deposit.getBalance());
        Assertions.assertEquals(1, deposit.getOperationsList().size());
    }

    @Test
    @Order(3)
    void testDepositAndWithdrawOnBankAccount() {
        BankAccount bankAccount = bankAccountService.createBankAccount(new BankAccount());
        BankAccount deposit = bankAccountService.deposit(bankAccount.getAccountNumber(), 500.0);
        BankAccount withdraw = bankAccountService.withdraw(deposit.getAccountNumber(), 100.0);
        Assertions.assertEquals(3, withdraw.getAccountNumber());
        Assertions.assertEquals(400.0, withdraw.getBalance());
        Assertions.assertEquals(2, withdraw.getOperationsList().size());
    }

    @Test
    @Order(4)
    void testStatementPrintingOnBankAccount() {
        BankAccount bankAccount = bankAccountService.createBankAccount(new BankAccount());
        BankAccount deposit = bankAccountService.deposit(bankAccount.getAccountNumber(), 500.0);
        BankAccount withdraw = bankAccountService.withdraw(deposit.getAccountNumber(), 100.0);

        Assertions.assertEquals(4, withdraw.getAccountNumber());
        Assertions.assertEquals(400.0, withdraw.getBalance());
        Assertions.assertEquals(2, withdraw.getOperationsList().size());
        String statementPrinting = bankAccountService.statementPrinting(withdraw.getAccountNumber());
        Operation operation1 = new Operation(1, OperationType.DEPOSIT, LocalDateTime.now(),500.0);
        Operation operation2 = new Operation(2, OperationType.WITHDRAWAL, LocalDateTime.now(),100.0);
        Assert.hasText(operation1 +operation2.toString(),statementPrinting);
    }
}

