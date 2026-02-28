import java.util.ArrayDeque;
import java.util.Deque;

/**
 * =============================================================
 * MAIN CLASS - UseCase7PalindromeCheckerApp
 * =============================================================
 *
 * Use Case 7: Deque-Based Optimized Palindrome Checker
 *
 * Description:
 * This program checks whether a given string is a palindrome
 * using a Deque (Double Ended Queue). Characters are inserted
 * into the deque and compared from both ends.
 *
 * Data Structure Used: Deque<Character>
 *
 * @version 1.0
 * =============================================================
 */

public class UseCase7PalindromeCheckerApp {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Hardcoded input string
        String input = "level";

        // Create Deque
        Deque<Character> deque = new ArrayDeque<>();

        // Insert characters into deque
        for (int i = 0; i < input.length(); i++) {
            deque.addLast(input.charAt(i));
        }

        boolean isPalindrome = true;

        // Compare front and rear elements
        while (deque.size() > 1) {

            char front = deque.removeFirst();
            char rear  = deque.removeLast();

            if (front != rear) {
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