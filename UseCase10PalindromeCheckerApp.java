public class UseCase10PalindromeCheckerApp {

    public static boolean isPalindrome(String input) {

        String normalized = input.replaceAll("\\s+", "").toLowerCase();

        int left = 0;
        int right = normalized.length() - 1;

        while (left < right) {
            if (normalized.charAt(left) != normalized.charAt(right))
                return false;

            left++;
            right--;
        }

        return true;
    }

    public static void main(String[] args) {

        String input = "A man a plan a canal Panama";

        boolean result = isPalindrome(input);

        System.out.println("Input String : " + input);

        if (result)
            System.out.println("Result : It is a Palindrome.");
        else
            System.out.println("Result : It is NOT a Palindrome.");
    }
}