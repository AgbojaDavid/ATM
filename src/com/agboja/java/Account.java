package com.agboja.java;

import java.util.List;
import java.util.ArrayList;

public class Account {
    private int accountNumber;
    private double balance;
    private int PIN;
    private List<Transaction> transactionHistory; // Assuming you have a field for transaction history

    public Account(int accountNumber, double balance, int PIN) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.PIN = PIN;
        this.transactionHistory = new ArrayList<>(); // Initialize transaction history list
    }

    // Add a method to verify PIN
    public boolean verifyPIN(int enteredPIN) {
        return this.PIN == enteredPIN;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        transactionHistory.add(new Transaction(TransactionType.DEPOSIT, amount));
        balance += amount;
    }

    public boolean withdraw(double amount) {
        if (balance >= amount) {
            transactionHistory.add(new Transaction(TransactionType.WITHDRAWAL, amount));
            balance -= amount;
            return true;
        }
        return false;
    }

    public void transfer(double amount, Account receiver) {
        if (balance >= amount) {
            transactionHistory.add(new Transaction(TransactionType.TRANSFER, amount));
            receiver.deposit(amount);
            balance -= amount;
        } else {
            System.out.println("Insufficient funds for transfer.");
        }
    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    public void printTransactionHistory(List<Transaction> transactions) {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }
    }
}

