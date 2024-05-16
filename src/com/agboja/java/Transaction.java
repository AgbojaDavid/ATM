package com.agboja.java;

import java.time.LocalDateTime;

public class Transaction {
    private LocalDateTime dateTime;
    private TransactionType type;
    private double amount;

    public Transaction(TransactionType type, double amount) {
        this.dateTime = LocalDateTime.now();
        this.type = type;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Date: " + dateTime + ", Type: " + type + ", Amount: " + amount;
    }

}

