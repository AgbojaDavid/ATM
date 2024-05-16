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

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter deposit amount:");
        double depositAmount = scanner.nextDouble();
        atm.deposit(depositAmount);

        System.out.println("Enter withdrawal amount:");
        double withdrawalAmount = scanner.nextDouble();
        atm.withdraw(withdrawalAmount);

        System.out.println("Enter transfer amount :₦");
        double transferAmount = scanner.nextDouble();
        System.out.println("Enter recipient account number:");
        int recipientAccountNumber = scanner.nextInt();
        atm.transfer(transferAmount, recipientAccountNumber);

        System.out.println("Account 1 Balance: " + account1.getBalance());
        System.out.println("Account 2 Balance: " + account2.getBalance());

        System.out.println("\nTransaction History:");
        atm.printTransactionHistory(atm.getTransactionHistory());

//        scanner.close(); // Close the scanner after usage
    }
}

