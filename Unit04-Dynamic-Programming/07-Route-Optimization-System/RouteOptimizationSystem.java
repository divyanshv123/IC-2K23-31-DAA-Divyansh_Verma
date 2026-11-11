import java.util.Arrays;

public class RouteOptimizationSystem {

    static final int INF = 999999;

    public static int[][] computeAllPairsShortestPaths(int[][] graph, int n) {
        int[][] dist = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    public static void optimizeDeliveryRoute(String[] locations, int[][] distMatrix) {
        int n = locations.length;
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
                        int cost = dp[mask][u] + distMatrix[u][v];

                        if (cost < dp[nextMask][v]) {
                            dp[nextMask][v] = cost;
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
                int tourCost = dp[finalMask][u] + distMatrix[u][0];
                if (tourCost < minCost) {
                    minCost = tourCost;
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

        int[] unoptimizedOrder = {0, 2, 1, 4, 3, 0};
        int unoptimizedCost = 0;
        for (int i = 0; i < n; i++) {
            unoptimizedCost += distMatrix[unoptimizedOrder[i]][unoptimizedOrder[i + 1]];
        }

        System.out.println("==========================================================================");
        System.out.println("                 LOGISTICS ROUTE OPTIMIZATION SYSTEM                      ");
        System.out.println("           (Dynamic Programming Optimization for Delivery Fleet)          ");
        System.out.println("==========================================================================");

        System.out.println("Delivery Hubs / Locations:");
        for (int i = 0; i < n; i++) {
            System.out.println("  [" + i + "] " + locations[i]);
        }
        System.out.println("--------------------------------------------------------------------------");

        System.out.print("Optimized Delivery Sequence: ");
        for (int i = 0; i <= n; i++) {
            System.out.print(locations[tour[i]] + (i < n ? " -> " : ""));
        }
        System.out.println();

        System.out.println("--------------------------------------------------------------------------");
        System.out.println("Optimized Route Distance:   " + minCost + " km");
        System.out.println("Unoptimized Route Distance: " + unoptimizedCost + " km");
        double savings = ((double) (unoptimizedCost - minCost) / unoptimizedCost) * 100.0;
        System.out.printf("Total Distance Saved:       %d km (%.2f%% efficiency improvement)%n",
                (unoptimizedCost - minCost), savings);
        System.out.println("==========================================================================");
    }

    public static void main(String[] args) {
        String[] hubs = {
            "Central Depot",
            "North Warehouse",
            "East Terminal",
            "South Hub",
            "West Center"
        };

        int[][] directRoads = {
            {0, 12, 29, 22, 18},
            {12, 0, 19, 35, 25},
            {29, 19, 0, 15, 30},
            {22, 35, 15, 0, 11},
            {18, 25, 30, 11, 0}
        };

        int[][] shortestDistances = computeAllPairsShortestPaths(directRoads, hubs.length);
        optimizeDeliveryRoute(hubs, shortestDistances);
    }
}
