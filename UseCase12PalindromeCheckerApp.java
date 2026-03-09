import java.util.*;

interface PalindromeStrategy {
    boolean check(String input);
}

class StackStrategy implements PalindromeStrategy {

    public boolean check(String input) {

        Stack<Character> stack = new Stack<>();

        for (char c : input.toCharArray())
            stack.push(c);

        for (char c : input.toCharArray())
            if (c != stack.pop())
                return false;

        return true;
    }
}

class DequeStrategy implements PalindromeStrategy {

    public boolean check(String input) {

        Deque<Character> deque = new ArrayDeque<>();

        for (char c : input.toCharArray())
            deque.addLast(c);

        while (deque.size() > 1)
            if (!deque.removeFirst().equals(deque.removeLast()))
                return false;

        return true;
    }
}

class PalindromeService {

    private PalindromeStrategy strategy;

    public PalindromeService(PalindromeStrategy strategy) {
        this.strategy = strategy;
    }

    public boolean checkPalindrome(String input) {
        return strategy.check(input);
    }
}

public class UseCase12PalindromeCheckerApp {

    public static void main(String[] args) {

        String input = "level";

        PalindromeService service =
                new PalindromeService(new StackStrategy());

        boolean result = service.checkPalindrome(input);

        System.out.println("Using Stack Strategy:");
        System.out.println("Input String : " + input);
        System.out.println(result ? "Result : It is a Palindrome."
                                  : "Result : It is NOT a Palindrome.");

        service = new PalindromeService(new DequeStrategy());

        result = service.checkPalindrome(input);

        System.out.println("\nUsing Deque Strategy:");
        System.out.println("Input String : " + input);
        System.out.println(result ? "Result : It is a Palindrome."
                                  : "Result : It is NOT a Palindrome.");
    }
}