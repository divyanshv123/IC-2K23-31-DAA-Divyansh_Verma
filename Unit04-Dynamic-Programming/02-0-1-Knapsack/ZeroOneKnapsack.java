import java.util.Scanner;

public class ZeroOneKnapsack {

    static int findMaxValue(int[] weight, int[] profit, int capacity, int count) {

        int[][] table = new int[count + 1][capacity + 1];

        for (int i = 1; i <= count; i++) {

            for (int bag = 0; bag <= capacity; bag++) {

                if (weight[i - 1] <= bag) {

                    int include = profit[i - 1] +
                            table[i - 1][bag - weight[i - 1]];

                    int exclude = table[i - 1][bag];

                    table[i][bag] = Math.max(include, exclude);

                } else {
                    table[i][bag] = table[i - 1][bag];
                }
            }
        }

        return table[count][capacity];
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("       KNAPSACK PROBLEM");
        System.out.println("==================================");

        System.out.print("How many items are available? ");
        int count = sc.nextInt();

        if (count <= 0) {
            System.out.println("Please enter a valid number of items.");
            sc.close();
            return;
        }

        int[] weight = new int[count];
        int[] profit = new int[count];

        System.out.println("\nEnter the weight of each item:");

        for (int i = 0; i < count; i++) {
            System.out.print("Item " + (i + 1) + ": ");
            weight[i] = sc.nextInt();

            if (weight[i] <= 0) {
                System.out.println("Weight must be greater than zero.");
                sc.close();
                return;
            }
        }

        System.out.println("\nEnter the value of each item:");

        for (int i = 0; i < count; i++) {
            System.out.print("Item " + (i + 1) + ": ");
            profit[i] = sc.nextInt();

            if (profit[i] < 0) {
                System.out.println("Value cannot be negative.");
                sc.close();
                return;
            }
        }

        System.out.print("\nEnter maximum bag capacity: ");
        int capacity = sc.nextInt();

        if (capacity < 0) {
            System.out.println("Capacity cannot be negative.");
            sc.close();
            return;
        }

        int answer = findMaxValue(weight, profit, capacity, count);

        System.out.println("\n========== FINAL RESULT ==========");
        System.out.println("Number of items  : " + count);
        System.out.println("Bag capacity     : " + capacity);
        System.out.println("Maximum value    : " + answer);
        System.out.println("==================================");

        sc.close();
    }
}
