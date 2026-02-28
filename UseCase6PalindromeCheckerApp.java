import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * =============================================================
 * MAIN CLASS - UseCase6PalindromeCheckerApp
 * =============================================================
 *
 * Use Case 6: Queue + Stack Based Palindrome Check
 *
 * Description:
 * This program demonstrates palindrome validation
 * using both Queue (FIFO) and Stack (LIFO) data structures.
 *
 * Data Structures Used: Queue<Character>, Stack<Character>
 *
 * @version 1.0
 * =============================================================
 */

public class UseCase6PalindromeCheckerApp {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Hardcoded input string
        String input = "madam";

        // Create Stack and Queue
        Stack<Character> stack = new Stack<>();
        Queue<Character> queue = new LinkedList<>();

        // Insert characters into both structures
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i);
            stack.push(ch);        // LIFO
            queue.add(ch);         // FIFO (enqueue)
        }

        boolean isPalindrome = true;

        // Compare dequeue (FIFO) with pop (LIFO)
        for (int i = 0; i < input.length(); i++) {

            char fromStack = stack.pop();     // LIFO removal
            char fromQueue = queue.remove();  // FIFO removal

            if (fromStack != fromQueue) {
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