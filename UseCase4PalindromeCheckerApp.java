/**
 * =============================================================
 * MAIN CLASS - UseCase4PalindromeCheckerApp
 * =============================================================
 *
 * Use Case 4: Character Array Based Palindrome Check
 *
 * Description:
 * This program checks whether a given string is a palindrome
 * by converting it into a character array and applying
 * the two-pointer technique.
 *
 * Data Structure Used: char[]
 *
 * @version 1.0
 * =============================================================
 */

public class UseCase4PalindromeCheckerApp {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {

        // Hardcoded string for demonstration
        String input = "radar";

        // Convert string to character array
        char[] characters = input.toCharArray();

        // Two-pointer initialization
        int left = 0;
        int right = characters.length - 1;

        boolean isPalindrome = true;

        // Compare characters using two-pointer technique
        while (left < right) {

            if (characters[left] != characters[right]) {
                isPalindrome = false;
                break;
            }

            left++;
            right--;
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