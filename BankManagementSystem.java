import java.util.ArrayList;
import java.util.Scanner;

class Account {
    private int accountNumber;
    private String name;
    private double balance;

    public Account(int accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Amount deposited successfully.");
        } else {
            System.out.println("Invalid amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount.");
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            balance -= amount;
            System.out.println("Amount withdrawn successfully.");
        }
    }

    public void displayAccount() {
        System.out.println("--------------------------------");
        System.out.println("Account Number : " + accountNumber);
        System.out.println("Account Holder : " + name);
        System.out.printf("Balance       : ₹%.2f%n", balance);
        System.out.println("--------------------------------");
    }
}

public class BankManagementSystem {

    static Scanner scanner = new Scanner(System.in);
    static ArrayList<Account> accounts = new ArrayList<>();

    public static Account findAccount(int accountNumber) {
        for (Account account : accounts) {
            if (account.getAccountNumber() == accountNumber) {
                return account;
            }
        }
        return null;
    }

    public static void createAccount() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();

        if (findAccount(accountNumber) != null) {
            System.out.println("Account already exists.");
            return;
        }

        System.out.print("Enter account holder name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial deposit: ₹");
        double balance = scanner.nextDouble();

        if (balance < 0) {
            System.out.println("Invalid initial deposit.");
            return;
        }

        accounts.add(new Account(accountNumber, name, balance));

        System.out.println("Account created successfully.");
    }

    public static void depositMoney() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to deposit: ₹");
        double amount = scanner.nextDouble();

        account.deposit(amount);
    }

    public static void withdrawMoney() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter amount to withdraw: ₹");
        double amount = scanner.nextDouble();

        account.withdraw(amount);
    }

    public static void checkBalance() {
        System.out.print("Enter account number: ");
        int accountNumber = scanner.nextInt();

        Account account = findAccount(accountNumber);

        if (account == null) {
            System.out.println("Account not found.");
        } else {
            account.displayAccount();
        }
    }

    public static void main(String[] args) {

        while (true) {
            System.out.println("\n========== BANK MANAGEMENT SYSTEM ==========");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Display All Accounts");
            System.out.println("6. Exit");
            System.out.println("============================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    createAccount();
                    break;

                case 2:
                    depositMoney();
                    break;

                case 3:
                    withdrawMoney();
                    break;

                case 4:
                    checkBalance();
                    break;

                case 5:
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts available.");
                    } else {
                        for (Account account : accounts) {
                            account.displayAccount();
                        }
                    }
                    break;

                case 6:
                    System.out.println("Thank you for using Bank Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}
