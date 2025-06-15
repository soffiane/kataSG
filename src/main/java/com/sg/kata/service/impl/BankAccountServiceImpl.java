package com.sg.kata.service.impl;

import com.sg.kata.entity.BankAccount;
import com.sg.kata.entity.Operation;
import com.sg.kata.entity.repository.BankAccountRepository;
import com.sg.kata.entity.repository.OperationRepository;
import com.sg.kata.enums.OperationType;
import com.sg.kata.exception.ResourceNotFoundException;
import com.sg.kata.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@Transactional
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private OperationRepository operationRepository;

    @Override
    public BankAccount getBankAccountById(Integer accountNumber) {
        Optional<BankAccount> bankAccountDb = this.bankAccountRepository.findById(accountNumber);
        if (bankAccountDb.isPresent()) {
            return bankAccountDb.get();
        } else {
            throw new ResourceNotFoundException("No bank account found with those credentials");
        }
    }

    public BankAccount createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public BankAccount deposit(Integer accountNumber, Double amount) {
        Optional<BankAccount> bankAccountDb = this.bankAccountRepository.findById(accountNumber);
        if (bankAccountDb.isPresent()) {
            BankAccount bankAccount = bankAccountDb.get();
            bankAccount.setBalance(bankAccount.getBalance() + amount);
            Operation operation = new Operation();
            operation.setDateOperation(LocalDateTime.now());
            operation.setAmount(amount);
            operation.setOperation(OperationType.DEPOSIT);
            operationRepository.save(operation);
            bankAccount.getOperationsList().add(operation);
            bankAccountRepository.save(bankAccount);
            return bankAccount;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + accountNumber);
        }
    }

    @Override
    public BankAccount withdraw(Integer accountNumber, Double amount) {
        Optional<BankAccount> productDb = this.bankAccountRepository.findById(accountNumber);
        if (productDb.isPresent()) {
            BankAccount bankAccount = productDb.get();
            bankAccount.setBalance(bankAccount.getBalance() - amount);
            Operation operation = new Operation();
            operation.setDateOperation(LocalDateTime.now());
            operation.setAmount(amount);
            operation.setOperation(OperationType.WITHDRAWAL);
            operationRepository.save(operation);
            bankAccount.getOperationsList().add(operation);
            bankAccountRepository.save(bankAccount);
            return bankAccount;
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + accountNumber);
        }
    }

    @Override
    public String statementPrinting(Integer accountNumber) {
        Optional<BankAccount> bankAccount = bankAccountRepository.findById(accountNumber);
        if (bankAccount.isPresent()) {
            return bankAccount.get().getOperationsList().toString();
        } else {
            throw new ResourceNotFoundException("Record not found with id : " + accountNumber);
        }
    }
}
