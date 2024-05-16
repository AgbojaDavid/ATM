package com.agboja.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class ATM {
    private Map<Integer, Account> accounts;
    private Account currentAccount; // Declare currentAccount as an instance variable
    private List<Transaction> transactionHistory;

    public ATM() {
        this.accounts = new HashMap<>();
        this.transactionHistory = new ArrayList<>();
        this.currentAccount = null; // Initialize currentAccount to null
    }

    public void addAccount(Account account) {
        accounts.put(account.getAccountNumber(), account); // Add account to the map
    }

    public void start() {
        System.out.println("WELCOME ");
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter Account Number:");
        int accountNumber = scanner.nextInt();
        System.out.println("Enter PIN:");
        int pin = scanner.nextInt();

        Account acc = accounts.get(accountNumber); // Change variable name to acc
        if (acc != null && acc.verifyPIN(pin)) { // Change variable name to acc
            this.currentAccount = acc; // Assign to instance variable
            menu();
        } else {
            System.out.println("Invalid Account Number or PIN. Exiting...");
            this.currentAccount = null; // Set currentAccount to null if invalid
//			return; // Terminate the method here
        }
//		scanner.close();
    }

    public void menu() {
//		int selectedAccountNumber; // Declare the variable here
        System.out.println("\nSelect an operation:");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. View Balance");
        System.out.println("5. Transaction History");
        System.out.println("6. Exit");

        Scanner scanner = new Scanner(System.in);
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                System.out.println("Enter Deposit Amount:");
                double depositAmount = scanner.nextDouble();
                if (currentAccount != null) {
                    currentAccount.deposit(depositAmount);
                    transactionHistory.add(new Transaction(TransactionType.DEPOSIT, depositAmount));
                    System.out.println("Deposit successful.");
                } else {
                    System.out.println("No account selected.");
                }
                break;
            case 2:
                System.out.println("Enter Withdrawal Amount:");
                double withdrawalAmount = scanner.nextDouble();
                if (currentAccount != null) {
                    if (currentAccount.withdraw(withdrawalAmount)) {
                        transactionHistory.add(new Transaction(TransactionType.WITHDRAWAL, withdrawalAmount));
                        System.out.println("Withdrawal successful.");
                    } else {
                        System.out.println("Insufficient funds.");
                    }
                } else {
                    System.out.println("No account selected.");
                }
                break;
            case 3:
                System.out.println("Enter Transfer Amount: ");
                double transferAmount = scanner.nextDouble();
                System.out.println("Enter Recipient Account Number:");
                int recipientAccountNumber = scanner.nextInt();
                transfer(transferAmount, recipientAccountNumber);
                break;
            case 4:
//			System.out.println("Enter Account Number:");
//			selectedAccountNumber = scanner.nextInt();
//			currentAccount = accounts.get(selectedAccountNumber); // Update currentAccount with the newly selected
//																	// account
//			if (currentAccount != null) {
                System.out.println("Account Balance: " + currentAccount.getBalance());
//			} else {
//				System.out.println("Account not found.");
//			}
                break;
            case 5:
//			System.out.println("Enter Account Number:");
//			selectedAccountNumber = scanner.nextInt();
//			currentAccount = accounts.get(selectedAccountNumber); // Update currentAccount with the newly selected
//																	// account
//			if (currentAccount != null) {
                printTransactionHistory(currentAccount.getTransactionHistory());
//			} else {
//				System.out.println("No account selected.");
//			}
                break;
            case 6:
                System.out.println("Thank You For Banking With Us");
                System.out.println("Exiting...");
                System.out.println("Take Your Card");
//			scanner.close();
                return;
            default:
                System.out.println("Invalid Selection");
                break;
        }
//		scanner.close();
        menu(); // After completing an operation, show menu again
    }

    public void deposit(double amount) {
        if (currentAccount != null) {
            currentAccount.deposit(amount);
            transactionHistory.add(new Transaction(TransactionType.DEPOSIT, amount)); // Add to global history
            System.out.println("Deposit successful.");
        } else {
            System.out.println("No account selected.");
        }
    }


    public void withdraw(double amount) {
        if (currentAccount != null) {
            if (currentAccount.withdraw(amount)) {

                transactionHistory.add(new Transaction(TransactionType.WITHDRAWAL, amount)); // Add to global history
                System.out.println("Withdrawal successful.");
            } else {
                System.out.println("Insufficient funds.");
            }
        } else {
            System.out.println("No account selected.");
        }
    }



    public void transfer(double amount, int recipientAccountNumber) {
        Account recipient = accounts.get(recipientAccountNumber);

        if (recipient != null) {
            currentAccount.transfer(amount, recipient);
            transactionHistory.add(new Transaction(TransactionType.TRANSFER, amount)); // Add to global history
            System.out.println("Transfer successful.");
        } else {
            System.out.println("recipient account not found.");
        }
    }

//	private Account getAccountByNumber(int accountNumber) {
//		// This method should retrieve the account by its number from a database or
//		// repository
//		// For simplicity, I'm assuming a single account for demonstration purposes
//		Account acc = accounts.get(accountNumber);
//		return acc != null && acc.getAccountNumber() == accountNumber ? acc : null;
//
//	}

    public void printTransactionHistory(List<Transaction> transactions) {
        System.out.println("Transaction History:");
        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

    }

    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }
}
