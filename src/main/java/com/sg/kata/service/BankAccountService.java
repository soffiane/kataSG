package com.sg.kata.service;

import com.sg.kata.entity.BankAccount;
import com.sg.kata.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface BankAccountService {

    BankAccount getBankAccountById(Integer accountNumber) throws ResourceNotFoundException;

    BankAccount createBankAccount(BankAccount bankAccount);

    BankAccount deposit(Integer accountNumber,Double amount) throws ResourceNotFoundException;

    BankAccount withdraw(Integer accountNumber, Double amount) throws ResourceNotFoundException;

    String statementPrinting(Integer accountNumber) throws ResourceNotFoundException;

}
