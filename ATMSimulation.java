import java.util.Scanner;

public class ATMSimulation {
    public static void main(String[] args) {
        // Authentication settings
        try (Scanner scanner = new Scanner(System.in)) {
            // Authentication settings
            final int CORRECT_PIN = 1234;
            int attempts = 0;
            final int MAX_ATTEMPTS = 3;
            boolean isAuthenticated = false;
            
            // Account data and history array
            double balance = 1000.0;
            String[] transactionHistory = new String[50];
            int transactionCount = 0;
            
            // PIN verification loop
            while (attempts < MAX_ATTEMPTS) {
                System.out.print("Enter your PIN: ");
                int enteredPin = scanner.nextInt();
                
                if (enteredPin == CORRECT_PIN) {
                    isAuthenticated = true;
                    System.out.println("Login successful!\n");
                    break;
                } else {
                    attempts++;
                    System.out.println("Incorrect PIN. Attempts left: " + (MAX_ATTEMPTS - attempts));
                }
            }
            
            if (!isAuthenticated) {
                System.out.println("Maximum attempts reached. Card blocked.");
                scanner.close();
                return;
            }
            
            // Main ATM menu loop
            int choice = 0;
            while (choice != 5) {
                System.out.println("=== ATM Main Menu ===");
                System.out.println("1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. View Transaction History");
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1 -> System.out.println("Current Balance: $" + balance + "\n");
                    
                    case 2 -> {
                        System.out.print("Enter amount to deposit: ");
                        double depositAmount = scanner.nextDouble();
                        if (depositAmount > 0) {
                            balance += depositAmount;
                            transactionHistory[transactionCount] = "Deposited: $" + depositAmount;
                            transactionCount++;
                            System.out.println("Deposit successful. Your New Balance: $" + balance + "\n");
                        } else {
                            System.out.println("Invalid amount.\n");
                        }
                    }
                    
                    case 3 -> {
                        System.out.print("Enter amount to withdraw: ");
                        double withdrawAmount = scanner.nextDouble();
                        if (withdrawAmount > 0 && withdrawAmount <= balance) {
                            balance -= withdrawAmount;
                            transactionHistory[transactionCount] = "Withdrew: $" + withdrawAmount;
                            transactionCount++;
                            System.out.println("Withdrawal successful. Your New Balance: $" + balance + "\n");
                        } else if (withdrawAmount > balance) {
                            System.out.println("Error: Insufficient balance. Your Current Balance: $" + balance + "\n");
                        } else {
                            System.out.println("Invalid amount.\n");
                        }
                    }
                    
                    case 4 -> {
                        System.out.println("--- Transaction History ---");
                        if (transactionCount == 0) {
                            System.out.println("No transactions yet.");
                        } else {
                            for (int i = 0; i < transactionCount; i++) {
                                System.out.println((i + 1) + ". " + transactionHistory[i]);
                            }
                        }
                        System.out.println();
                    }

                    case 5 -> System.out.println("Thank you for using the ATM. Goodbye!");
                    
                    default -> System.out.println("Invalid option. Please try again.\n");
                }
            }
        }
    }
}