import java.util.Scanner;

class BankAccount {
    private double balance;

    public BankAccount(double initialBalance) {
        this.balance = initialBalance;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited: $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew: $" + amount);
        } else if (amount > balance) {
            System.out.println("Insufficient balance.");
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}

class ATM {
    public BankAccount account;

    public ATM(BankAccount account) {
        this.account = account;
    }

    public void checkBalance() {
        System.out.println("Current balance: $" + account.getBalance());
    }

    public void depositMoney(double amount) {
        account.deposit(amount);
    }

    public void withdrawMoney(double amount) {
        account.withdraw(amount);
    }

    public void displayMenu() {
        System.out.println("\n--- ATM Menu ---");
        System.out.println("1. Check Balance");
        System.out.println("2. Deposit Money");
        System.out.println("3. Withdraw Money");
        System.out.println("4. Exit");
    }

    public void processUserChoice(int choice, Scanner scanner) {
        double amount;
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                System.out.print("Enter amount to deposit: ");
                amount = scanner.nextDouble();
                depositMoney(amount);
                break;
            case 3:
                System.out.print("Enter amount to withdraw: ");
                amount = scanner.nextDouble();
                withdrawMoney(amount);
                break;
            case 4:
                System.out.println("Exiting ATM system. Thank you!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }
}

public class Task_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount(1000.00); // Initialize with a balance of $1000
        ATM atm = new ATM(account);

        System.out.println("Welcome to the ATM!");
        int choice;

        do {
            atm.displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            atm.processUserChoice(choice, scanner);
        } while (choice != 4);

        scanner.close();
    }
}
