import java.util.Arrays;

public class TravelingSalespersonDP {

    static final int INF = 999999;

    public static void solveTSP(int[][] dist, int n) {
        int totalStates = 1 << n;
        int[][] dp = new int[totalStates][n];
        int[][] parent = new int[totalStates][n];

        for (int i = 0; i < totalStates; i++) {
            Arrays.fill(dp[i], INF);
            Arrays.fill(parent[i], -1);
        }

        dp[1][0] = 0;

        for (int mask = 1; mask < totalStates; mask++) {
            for (int u = 0; u < n; u++) {
                if ((mask & (1 << u)) == 0 || dp[mask][u] == INF) {
                    continue;
                }

                for (int v = 0; v < n; v++) {
                    if ((mask & (1 << v)) == 0) {
                        int nextMask = mask | (1 << v);
                        int newCost = dp[mask][u] + dist[u][v];

                        if (newCost < dp[nextMask][v]) {
                            dp[nextMask][v] = newCost;
                            parent[nextMask][v] = u;
                        }
                    }
                }
            }
        }

        int finalMask = (1 << n) - 1;
        int minCost = INF;
        int lastNode = -1;

        for (int u = 1; u < n; u++) {
            if (dp[finalMask][u] != INF) {
                int totalTourCost = dp[finalMask][u] + dist[u][0];
                if (totalTourCost < minCost) {
                    minCost = totalTourCost;
                    lastNode = u;
                }
            }
        }

        int[] tour = new int[n + 1];
        int currMask = finalMask;
        int currNode = lastNode;

        tour[n] = 0;
        for (int step = n - 1; step >= 1; step--) {
            tour[step] = currNode;
            int prevNode = parent[currMask][currNode];
            currMask ^= (1 << currNode);
            currNode = prevNode;
        }
        tour[0] = 0;

        System.out.println("TRAVELING SALESPERSON PROBLEM (DYNAMIC PROGRAMMING)");
        System.out.println("Number of Cities: " + n);
        System.out.println("---------------------------------------------------");
        System.out.println("Minimum Tour Cost: " + minCost);

        System.out.print("Optimal Tour: ");
        for (int i = 0; i <= n; i++) {
            System.out.print(tour[i] + (i < n ? " -> " : ""));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] dist = {
            {0, 10, 15, 20},
            {10, 0, 35, 25},
            {15, 35, 0, 30},
            {20, 25, 30, 0}
        };

        solveTSP(dist, n);
    }
}
