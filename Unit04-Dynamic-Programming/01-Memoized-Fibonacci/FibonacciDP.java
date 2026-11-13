import java.util.Arrays;
import java.util.Scanner;

public class FibonacciDP {

    static int[] cache;

    static int calculate(int position) {

        if (position <= 1) {
            return position;
        }

        if (cache[position] != -1) {
            return cache[position];
        }

        cache[position] = calculate(position - 1) + calculate(position - 2);

        return cache[position];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("     FIBONACCI NUMBER FINDER");
        System.out.println("=================================");

        System.out.print("Enter the position: ");
        int position = sc.nextInt();

        if (position < 0) {
            System.out.println("Invalid input! Enter a positive number.");
        } else if (position > 46) {
            System.out.println("Please enter a position between 0 and 46.");
        } else {

            cache = new int[position + 1];
            Arrays.fill(cache, -1);

            int answer = calculate(position);

            System.out.println("\n---------- RESULT ----------");
            System.out.println("Position       : " + position);
            System.out.println("Fibonacci Value: " + answer);
            System.out.println("----------------------------");
        }

        sc.close();
    }
}
