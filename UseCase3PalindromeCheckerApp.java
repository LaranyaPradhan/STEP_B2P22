public class UseCase3PalindromeCheckerApp {

    public static void main(String[] args) {

        String original = "level";

        String reversed = "";

        for (int i = original.length() - 1; i >= 0; i--) {
            reversed = reversed + original.charAt(i);
        }

        if (original.equals(reversed)) {
            System.out.println("Original String : " + original);
            System.out.println("Reversed String : " + reversed);
            System.out.println("Result : It is a Palindrome.");
        } else {
            System.out.println("Original String : " + original);
            System.out.println("Reversed String : " + reversed);
            System.out.println("Result : It is NOT a Palindrome.");
        }

        System.out.println("Program execution completed.");
    }
}
