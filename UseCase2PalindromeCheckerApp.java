/**
 * =============================================================
 * MAIN CLASS - UseCase2PalindromeCheckerApp
 * =============================================================
 *
 * Use Case 2: Print a Hardcoded Palindrome Result
 *
 * Description:
 * This program checks whether a hardcoded string
 * is a palindrome and prints the result.
 *
 * Data Structure Used: String
 *
 * @version 1.0
 * =============================================================
 */

public class UseCase2PalindromeCheckerApp {

    /**
     * Application entry point.
     * This method is invoked by the JVM.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Hardcoded string literal
        String word = "madam";

        // Reverse the string
        String reversed = "";

        for (int i = word.length() - 1; i >= 0; i--) {
            reversed = reversed + word.charAt(i);
        }

        // Conditional check using if-else
        if (word.equals(reversed)) {
            System.out.println("The word \"" + word + "\" is a Palindrome.");
        } else {
            System.out.println("The word \"" + word + "\" is NOT a Palindrome.");
        }

        System.out.println("Program execution completed.");
    }
}