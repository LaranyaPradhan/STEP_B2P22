import java.util.Scanner;

/**
 * ===============================================================
 *            PALINDROME CHECKER MANAGEMENT SYSTEM
 * ===============================================================
 *
 * Description:
 * Console-based application that allows users to:
 * 1. Check simple palindrome
 * 2. Check palindrome ignoring case
 * 3. Check palindrome ignoring case & spaces
 * 4. Exit application
 *
 * @version 1.0
 * ===============================================================
 */

public class UseCase1PalindromeCheckerApp {

    private static final String APP_NAME = 
            "Palindrome Checker Management System";
    private static final String VERSION = "1.0";

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        displayHeader();

        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice: ");

            int choice = getValidInteger(scanner);

            switch (choice) {

                case 1:
                    handleSimplePalindrome(scanner);
                    break;

                case 2:
                    handleCaseInsensitivePalindrome(scanner);
                    break;

                case 3:
                    handleAdvancedPalindrome(scanner);
                    break;

                case 4:
                    running = false;
                    System.out.println("\nExiting application...");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        System.out.println("Thank you for using the system.");
        scanner.close();
    }

    private static void displayHeader() {
        System.out.println("=================================================");
        System.out.println("        " + APP_NAME);
        System.out.println("=================================================");
        System.out.println("Version : " + VERSION);
        System.out.println("System initialized successfully.\n");
    }

    private static void displayMenu() {
        System.out.println("\n----------- MENU -----------");
        System.out.println("1. Check Simple Palindrome");
        System.out.println("2. Check Palindrome (Ignore Case)");
        System.out.println("3. Check Palindrome (Ignore Case & Spaces)");
        System.out.println("4. Exit");
        System.out.println("-----------------------------");
    }

    private static int getValidInteger(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter a number: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    private static void handleSimplePalindrome(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter string: ");
        String input = scanner.nextLine();

        if (isPalindrome(input)) {
            System.out.println("Result: It is a palindrome.");
        } else {
            System.out.println("Result: It is NOT a palindrome.");
        }
    }

    private static void handleCaseInsensitivePalindrome(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter string: ");
        String input = scanner.nextLine();

        if (isPalindrome(input.toLowerCase())) {
            System.out.println("Result: It is a palindrome.");
        } else {
            System.out.println("Result: It is NOT a palindrome.");
        }
    }

    private static void handleAdvancedPalindrome(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Enter string: ");
        String input = scanner.nextLine();

        String processed = input
                .replaceAll("\\s+", "")
                .toLowerCase();

        if (isPalindrome(processed)) {
            System.out.println("Result: It is a palindrome.");
        } else {
            System.out.println("Result: It is NOT a palindrome.");
        }
    }

    private static boolean isPalindrome(String str) {

        int left = 0;
        int right = str.length() - 1;

        while (left < right) {

            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }

            left++;
            right--;
        }

        return true;
    }
}