import java.util.Stack;

/**
 * =============================================================
 * MAIN CLASS - UseCase5PalindromeCheckerApp
 * =============================================================
 *
 * Use Case 5: Stack-Based Palindrome Checker
 *
 * Description:
 * This program checks whether a given string is a palindrome
 * using the Stack data structure (LIFO principle).
 *
 * Data Structure Used: Stack<Character>
 *
 * @version 1.0
 * =============================================================
 */

public class UseCase5PalindromeCheckerApp {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Hardcoded input string
        String input = "refer";

        // Create stack of characters
        Stack<Character> stack = new Stack<>();

        // Push characters into stack
        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));
        }

        boolean isPalindrome = true;

        // Pop characters and compare
        for (int i = 0; i < input.length(); i++) {

            char poppedChar = stack.pop();

            if (input.charAt(i) != poppedChar) {
                isPalindrome = false;
                break;
            }
        }

        // Display result
        System.out.println("Input String : " + input);

        if (isPalindrome) {
            System.out.println("Result : It is a Palindrome.");
        } else {
            System.out.println("Result : It is NOT a Palindrome.");
        }

        System.out.println("Program execution completed.");
    }
}