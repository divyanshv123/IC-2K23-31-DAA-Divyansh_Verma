import java.util.Scanner;

public class BinarySearch_DC {

    static int search(int[] numbers, int left, int right, int key) {

        if (left > right) {
            return -1;
        }

        int middle = left + (right - left) / 2;

        if (numbers[middle] == key) {
            return middle;
        }

        if (numbers[middle] < key) {
            return search(numbers, middle + 1, right, key);
        }

        return search(numbers, left, middle - 1, key);
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("     RECURSIVE BINARY SEARCH");

        System.out.print("Enter number of elements (1-100): ");
        int n = sc.nextInt();

        if (n < 1 || n > 100) {
            System.out.println("Invalid array size!");
            sc.close();
            return;
        }

        int[] numbers = new int[n];

        System.out.println("Enter elements in ascending order:");

        for (int i = 0; i < n; i++) {
            numbers[i] = sc.nextInt();

            if (i > 0 && numbers[i] < numbers[i - 1]) {
                System.out.println("Error: Array must be sorted!");
                sc.close();
                return;
            }
        }

        System.out.print("Enter value to search: ");
        int key = sc.nextInt();

        int index = search(numbers, 0, n - 1, key);

        System.out.println("\n SEARCH REPORT");

        if (index != -1) {
            System.out.println("Status       : SUCCESS");
            System.out.println("Search Value : " + key);
            System.out.println("Array Index  : " + index);
            System.out.println("Position     : " + (index + 1));
        } else {
            System.out.println("Status       : NOT FOUND");
            System.out.println("Search Value : " + key);
        }

        sc.close();
    }
}
