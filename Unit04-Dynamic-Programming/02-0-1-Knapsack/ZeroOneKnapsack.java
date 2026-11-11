public class ZeroOneKnapsack {

    public static void solveKnapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 0; i <= n; i++) {
            for (int w = 0; w <= capacity; w++) {
                if (i == 0 || w == 0) {
                    dp[i][w] = 0;
                } else if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        int maxProfit = dp[n][capacity];
        System.out.println("0/1 KNAPSACK OPTIMIZATION (DYNAMIC PROGRAMMING)");
        System.out.println("Knapsack Capacity: " + capacity);
        System.out.println("----------------------------------------------");
        System.out.println("Maximum Profit: " + maxProfit);

        System.out.println("\nSelected Items:");
        int remainingWeight = capacity;
        for (int i = n; i > 0 && maxProfit > 0; i--) {
            if (maxProfit != dp[i - 1][remainingWeight]) {
                System.out.println("Item " + i + " -> Weight: " + weights[i - 1] + ", Value: " + values[i - 1]);
                maxProfit -= values[i - 1];
                remainingWeight -= weights[i - 1];
            }
        }
    }

    public static void main(String[] args) {
        int[] values = {60, 100, 120};
        int[] weights = {10, 20, 30};
        int capacity = 50;

        solveKnapsack(values, weights, capacity);
    }
}
