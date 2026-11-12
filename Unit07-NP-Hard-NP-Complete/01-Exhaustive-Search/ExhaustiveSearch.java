public class ExhaustiveSearch {

    public static void exhaustiveKnapsack(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int totalCombinations = 1 << n;
        int bestValue = 0;
        int bestMask = 0;
        int totalStatesEvaluated = 0;

        System.out.println("EXHAUSTIVE (BRUTE-FORCE) SEARCH DEMONSTRATION");
        System.out.println("Problem: 0/1 Knapsack (Total Items: " + n + ", Capacity: " + capacity + ")");
        System.out.println("----------------------------------------------------------------------");
        System.out.printf("%-12s | %-16s | %-12s | %-12s | %-8s%n",
                "Subset Mask", "Binary Selection", "Total Weight", "Total Value", "Feasible");
        System.out.println("----------------------------------------------------------------------");

        for (int mask = 0; mask < totalCombinations; mask++) {
            totalStatesEvaluated++;
            int currentWeight = 0;
            int currentValue = 0;
            StringBuilder binaryRepresentation = new StringBuilder();

            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentWeight += weights[i];
                    currentValue += values[i];
                    binaryRepresentation.append("1");
                } else {
                    binaryRepresentation.append("0");
                }
            }

            boolean isFeasible = currentWeight <= capacity;
            String status = isFeasible ? "YES" : "NO (Exceeds)";

            System.out.printf("State %-6d | %-16s | %-12d | %-12d | %-8s%n",
                    mask, binaryRepresentation.toString(), currentWeight, currentValue, status);

            if (isFeasible && currentValue > bestValue) {
                bestValue = currentValue;
                bestMask = mask;
            }
        }

        System.out.println("----------------------------------------------------------------------");
        System.out.println("Total Candidate States Evaluated: " + totalStatesEvaluated);
        System.out.println("Optimal Feasible Value:           " + bestValue);
        System.out.print("Selected Item Indices (0-based):  ");
        for (int i = 0; i < n; i++) {
            if ((bestMask & (1 << i)) != 0) {
                System.out.print(i + " ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] values = {20, 30, 50, 70};
        int[] weights = {2, 5, 10, 5};
        int capacity = 15;

        exhaustiveKnapsack(values, weights, capacity);
    }
}
