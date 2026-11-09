import java.util.Scanner;

public class ComplexityAnalysis {

    static int linearSearch(int[] arr, int size, int target, int[] comparisons) {
        comparisons[0] = 0;

        for (int i = 0; i < size; i++) {
            comparisons[0]++;

            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] arr = new int[100];
        int size;
        int[] comparisons = new int[1];
        int result;

        System.out.println("===== COMPLEXITY ANALYSIS =====");

        System.out.print("Enter array size (1-100): ");
        size = sc.nextInt();

        if (size < 1 || size > 100) {
            System.out.println("Invalid array size.");
            sc.close();
            return;
        }

        System.out.println("Enter " + size + " array elements:");

        for (int i = 0; i < size; i++) {
            arr[i] = sc.nextInt();
        }

        System.out.println("\n--- BEST CASE ---");

        result = linearSearch(arr, size, arr[0], comparisons);

        System.out.println("Target Element: " + arr[0]);
        System.out.println("Comparisons: " + comparisons[0]);
        System.out.println("Time Complexity: O(1)");

        System.out.println("\n--- AVERAGE CASE ---");

        int middleElement = arr[size / 2];

        result = linearSearch(arr, size, middleElement, comparisons);

        System.out.println("Target Element: " + middleElement);
        System.out.println("Comparisons: " + comparisons[0]);
        System.out.println("Time Complexity: O(n)");

        System.out.println("\n--- WORST CASE ---");

        int worstTarget = arr[size - 1];

        if (worstTarget < 1000000) {
            worstTarget++;
        } else {
            worstTarget--;
        }

        result = linearSearch(arr, size, worstTarget, comparisons);

        System.out.println("Target Element: " + worstTarget);
        System.out.println("Comparisons: " + comparisons[0]);
        System.out.println("Time Complexity: O(n)");

        sc.close();
    }
}
