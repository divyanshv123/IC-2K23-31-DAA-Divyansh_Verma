public class FibonacciMemoization {

    public static long recursiveFib(int n) {
        if (n <= 1) {
            return n;
        }
        return recursiveFib(n - 1) + recursiveFib(n - 2);
    }

    public static long memoizedFib(int n, long[] memo) {
        if (n <= 1) {
            return n;
        }
        if (memo[n] != 0) {
            return memo[n];
        }
        memo[n] = memoizedFib(n - 1, memo) + memoizedFib(n - 2, memo);
        return memo[n];
    }

    public static long tabulatedFib(int n) {
        if (n <= 1) {
            return n;
        }
        long[] table = new long[n + 1];
        table[0] = 0;
        table[1] = 1;
        for (int i = 2; i <= n; i++) {
            table[i] = table[i - 1] + table[i - 2];
        }
        return table[n];
    }

    public static void main(String[] args) {
        int n = 40;

        System.out.println("FIBONACCI: RECURSION VS. DYNAMIC PROGRAMMING");
        System.out.println("Target Term (n): " + n);
        System.out.println("-----------------------------------------------------------------");

        long startMemo = System.nanoTime();
        long[] memo = new long[n + 1];
        long resultMemo = memoizedFib(n, memo);
        long endMemo = System.nanoTime();
        double timeMemo = (endMemo - startMemo) / 1000000.0;

        long startTab = System.nanoTime();
        long resultTab = tabulatedFib(n);
        long endTab = System.nanoTime();
        double timeTab = (endTab - startTab) / 1000000.0;

        long startRec = System.nanoTime();
        long resultRec = recursiveFib(n);
        long endRec = System.nanoTime();
        double timeRec = (endRec - startRec) / 1000000.0;

        System.out.printf("%-20s | %-15s | %-18s | %-12s%n",
                "Method", "Fibonacci Value", "Execution Time (ms)", "Complexity");
        System.out.println("-----------------------------------------------------------------");
        System.out.printf("%-20s | %-15d | %-18.4f | %-12s%n",
                "Memoization (Top-Down)", resultMemo, timeMemo, "O(n)");
        System.out.printf("%-20s | %-15d | %-18.4f | %-12s%n",
                "Tabulation (Bottom-Up)", resultTab, timeTab, "O(n)");
        System.out.printf("%-20s | %-15d | %-18.4f | %-12s%n",
                "Naive Recursion", resultRec, timeRec, "O(2^n)");
        System.out.println("-----------------------------------------------------------------");
    }
}
