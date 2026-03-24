public class UseCase4PalindromeCheckerApp {

    public static void main(String[] args) {

        String input = "radar";

        char[] characters = input.toCharArray();

        int left = 0;
        int right = characters.length - 1;

        boolean isPalindrome = true;

        while (left < right) {

            if (characters[left] != characters[right]) {
                isPalindrome = false;
                break;
            }

            left++;
            right--;
        }

        System.out.println("Input String : " + input);

        if (isPalindrome) {
            System.out.println("Result : It is a Palindrome.");
        } else {
            System.out.println("Result : It is NOT a Palindrome.");
        }

        System.out.println("Program execution completed.");
    }
}
