import java.util.Arrays;
import java.util.Scanner;

public class ArrayOperations {

    static void display(int[] arr) {
        System.out.println(Arrays.toString(arr));
    }

    static int findMax(int[] arr) {
        int max = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        return max;
    }

    static int findMin(int[] arr) {
        int min = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < min) {
                min = arr[i];
            }
        }

        return min;
    }

    static int search(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    static int sum(int[] arr) {
        int total = 0;

        for (int i = 0; i < arr.length; i++) {
            total += arr[i];
        }

        return total;
    }

    static void reverse(int[] arr) {
        int i = 0;
        int j = arr.length - 1;

        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;

            i++;
            j--;
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] arr = new int[n];

        System.out.println("Enter array elements:");

        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.print("Original array: ");
        display(arr);

        System.out.println("Maximum: " + findMax(arr));
        System.out.println("Minimum: " + findMin(arr));
        System.out.println("Sum: " + sum(arr));

        System.out.print("Enter element to search: ");
        int target = sc.nextInt();

        int index = search(arr, target);

        if (index != -1) {
            System.out.println("Element found at index: " + index);
        } else {
            System.out.println("Element not found");
        }

        reverse(arr);

        System.out.print("Reversed array: ");
        display(arr);

        sc.close();
    }
}
