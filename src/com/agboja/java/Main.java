package com.agboja.java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ATM atm = new ATM(); // Initialize ATM without a PIN

        Account account1 = new Account(123456789, 1000.0, 1234);
        Account account2 = new Account(987654321, 2000.0, 5678);

        atm.addAccount(account1);
        atm.addAccount(account2);

        atm.start();
    }
}

