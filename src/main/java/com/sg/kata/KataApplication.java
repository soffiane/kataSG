package com.sg.kata;

import com.sg.kata.entity.BankAccount;
import com.sg.kata.exception.ResourceNotFoundException;
import com.sg.kata.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.util.Scanner;

@SpringBootApplication
@ComponentScan
public class KataApplication implements CommandLineRunner {

    @Autowired
    BankAccountService bankAccountService;

    public static void main(String[] args) {
        SpringApplication.run(KataApplication.class, args);
    }

    @Override
    public void run(String[] args) {

        boolean exit = false;
        Integer accountNumber = null;
        BankAccount bankAccount = null;
        do {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Press 1 to login to your bank account:");
            System.out.println("Press 2 to create a new bank account:");
            System.out.println("Press 3 to withdraw from your bank account:");
            System.out.println("Press 4 to deposit money into your bank account:");
            System.out.println("Press 5 to print account statement:");
            System.out.println("Press 6 to exit:");
            int s = scanner.nextInt();

            switch (s) {
                case 1:
                    try {
                        System.out.println("Insert your account number");
                        bankAccount = bankAccountService.getBankAccountById(accountNumber);
                        System.out.println("Logged in on accountNumber "+bankAccount.getAccountNumber());
                    } catch (ResourceNotFoundException e) {
                        System.out.println("Bank account doesnt exists");
                    }
                    break;

                case 2:
                    System.out.println("Choose a password for your account");
                    bankAccount = bankAccountService.createBankAccount(new BankAccount());
                    accountNumber = bankAccount.getAccountNumber();
                    System.out.println("Bank account number " + accountNumber + " created successfully");
                    System.out.println("Your current balance is " + bankAccount.getBalance());
                    break;

                case 3:
                    try {
                        System.out.println("How much cash do you want to withdraw ?:");
                        bankAccountService.withdraw(bankAccount.getAccountNumber(), scanner.nextDouble());
                        System.out.println("Withdraw succes, your balance is now :" + bankAccountService.getBankAccountById(accountNumber).getBalance());
                    } catch (ResourceNotFoundException e) {
                        System.out.println("You dont own an account in our bank");
                    }
                    break;


                case 4:
                    try {
                        if (accountNumber == null) {
                            System.out.println("What is your bank account number ?:");
                            accountNumber = scanner.nextInt();
                        }
                        BankAccount bankAccountFound = bankAccountService.getBankAccountById(accountNumber);
                        System.out.println("How much cash do you want to deposit ?:");
                        bankAccountService.deposit(bankAccountFound.getAccountNumber(), scanner.nextDouble());
                        System.out.println("Deposit succes, your balance is now :" + bankAccountService.getBankAccountById(accountNumber).getBalance());
                    } catch (ResourceNotFoundException e) {
                        System.out.println("You dont own an account in our bank");
                    }
                    break;

                case 5:
                    try {
                        if (accountNumber == null) {
                            System.out.println("What is your bank account number ?:");
                            accountNumber = scanner.nextInt();
                        }
                        System.out.println(bankAccountService.statementPrinting(accountNumber));
                    } catch (ResourceNotFoundException e) {
                        System.out.println("You dont own an account in our bank");
                    }
                    break;

                case 6:
                    exit = true;
                    System.exit(0);
                    break;
            }
        } while (!exit);
    }
}
