import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Interface for transactions
interface Transaction {
    void execute(double amount);
}

// Abstract class for Account
abstract class Account {
    private String accountNumber;
    protected double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    // Abstract methods for transaction execution
    abstract void deposit(double amount);
    abstract void withdraw(double amount);
}

// Concrete subclass for SavingsAccount
class SavingsAccount extends Account implements Transaction {
    public SavingsAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Depositing $" + amount + " into savings account");
        this.execute(amount);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawing $" + amount + " from savings account");
        this.execute(-amount); // negative amount for withdrawal
    }

    @Override
    public void execute(double amount) {
        double newBalance = this.balance + amount;
        if (newBalance >= 0) {
            this.balance = newBalance;
            System.out.println("Transaction successful. New balance: $" + this.balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

// Concrete subclass for CheckingAccount
class CheckingAccount extends Account implements Transaction {
    public CheckingAccount(String accountNumber, double balance) {
        super(accountNumber, balance);
    }

    @Override
    public void deposit(double amount) {
        System.out.println("Depositing $" + amount + " into checking account");
        this.execute(amount);
    }

    @Override
    public void withdraw(double amount) {
        System.out.println("Withdrawing $" + amount + " from checking account");
        this.execute(-amount); // negative amount for withdrawal
    }

    @Override
    public void execute(double amount) {
        double newBalance = this.balance + amount;
        if (newBalance >= 0) {
            this.balance = newBalance;
            System.out.println("Transaction successful. New balance: $" + this.balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }
}

// Bank Management System class
public class BankManagement{
    private List<Account> accounts;
    private Scanner scanner;

    public BankManagement() {
        accounts = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nWelcome to the Bank Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Delete Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View Account Details");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createAccount();
                    break;
                case 2:
                    deleteAccount();
                    break;
                case 3:
                    deposit();
                    break;
                case 4:
                    withdraw();
                    break;
                case 5:
                    viewAccountDetails();
                    break;
                case 6:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a number from 1 to 6.");
            }
        }
        scanner.close();
    }

    private void createAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        System.out.print("Enter initial balance: ");
        double initialBalance = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter account type (1 for Savings, 2 for Checking): ");
        int accountType = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        if (accountType == 1) {
            accounts.add(new SavingsAccount(accountNumber, initialBalance));
            System.out.println("Savings account created successfully.");
        } else if (accountType == 2) {
            accounts.add(new CheckingAccount(accountNumber, initialBalance));
            System.out.println("Checking account created successfully.");
        } else {
            System.out.println("Invalid account type.");
        }
    }

    private void deleteAccount() {
        System.out.print("Enter account number to delete: ");
        String accountNumber = scanner.nextLine();

        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).getAccountNumber().equals(accountNumber)) {
                accounts.remove(i);
                System.out.println("Account deleted successfully.");
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private void deposit() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.print("Enter deposit amount: ");
                double amount = scanner.nextDouble();
                account.deposit(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private void withdraw() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.print("Enter withdrawal amount: ");
                double amount = scanner.nextDouble();
                account.withdraw(amount);
                return;
            }
        }
        System.out.println("Account not found.");
    }

    private void viewAccountDetails() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                System.out.println("Account Number: " + account.getAccountNumber());
                System.out.println("Balance: $" + account.getBalance());
                return;
            }
        }
        System.out.println("Account not found.");
    }

    public static void main(String[] args) {
        BankManagement bankManagementSystem = new BankManagement();
        bankManagementSystem.start();
    }
}
