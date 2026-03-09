import java.util.*;

public class UseCase13PalindromeCheckerApp {

    public static boolean twoPointer(String input) {
        int left = 0;
        int right = input.length() - 1;

        while (left < right) {
            if (input.charAt(left) != input.charAt(right))
                return false;

            left++;
            right--;
        }
        return true;
    }

    public static boolean stackMethod(String input) {
        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray())
            stack.push(c);

        for (char c : input.toCharArray())
            if (c != stack.pop())
                return false;

        return true;
    }

    public static boolean reverseMethod(String input) {
        String reversed = "";

        for (int i = input.length() - 1; i >= 0; i--)
            reversed += input.charAt(i);

        return input.equals(reversed);
    }

    public static void main(String[] args) {

        String input = "racecar";

        long start1 = System.nanoTime();
        boolean r1 = twoPointer(input);
        long end1 = System.nanoTime();

        long start2 = System.nanoTime();
        boolean r2 = stackMethod(input);
        long end2 = System.nanoTime();

        long start3 = System.nanoTime();
        boolean r3 = reverseMethod(input);
        long end3 = System.nanoTime();

        System.out.println("Input String : " + input);

        System.out.println("\nTwo Pointer Method:");
        System.out.println("Result : " + r1);
        System.out.println("Time : " + (end1 - start1) + " ns");

        System.out.println("\nStack Method:");
        System.out.println("Result : " + r2);
        System.out.println("Time : " + (end2 - start2) + " ns");

        System.out.println("\nReverse String Method:");
        System.out.println("Result : " + r3);
        System.out.println("Time : " + (end3 - start3) + " ns");
    }
}