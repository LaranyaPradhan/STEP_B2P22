class PalindromeChecker {

    public boolean checkPalindrome(String input) {

        char[] arr = input.toCharArray();

        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            if (arr[left] != arr[right])
                return false;

            left++;
            right--;
        }

        return true;
    }
}

public class UseCase11PalindromeCheckerApp {

    public static void main(String[] args) {

        PalindromeChecker checker = new PalindromeChecker();

        String input = "madam";

        boolean result = checker.checkPalindrome(input);

        System.out.println("Input String : " + input);

        if (result)
            System.out.println("Result : It is a Palindrome.");
        else
            System.out.println("Result : It is NOT a Palindrome.");
    }
}