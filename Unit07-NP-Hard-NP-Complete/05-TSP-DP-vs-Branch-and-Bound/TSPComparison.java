import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

public class TSPComparison {

    static final int INF = 999999;

    static class Node {
        List<Integer> path;
        int[][] reducedMatrix;
        int cost;
        int vertex;
        int level;

        Node(int n) {
            path = new ArrayList<>();
            reducedMatrix = new int[n][n];
        }
    }

    public static int solveTSP_DP(int[][] dist, int n) {
        int totalStates = 1 << n;
        int[][] dp = new int[totalStates][n];

        for (int i = 0; i < totalStates; i++) {
            Arrays.fill(dp[i], INF);
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
                        }
                    }
                }
            }
        }

        int finalMask = (1 << n) - 1;
        int minCost = INF;

        for (int u = 1; u < n; u++) {
            if (dp[finalMask][u] != INF) {
                int totalTourCost = dp[finalMask][u] + dist[u][0];
                if (totalTourCost < minCost) {
                    minCost = totalTourCost;
                }
            }
        }

        return minCost;
    }

    public static int reduceMatrix(int[][] matrix, int n) {
        int reductionCost = 0;

        for (int i = 0; i < n; i++) {
            int rowMin = INF;
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] < rowMin) {
                    rowMin = matrix[i][j];
                }
            }
            if (rowMin != INF && rowMin > 0) {
                reductionCost += rowMin;
                for (int j = 0; j < n; j++) {
                    if (matrix[i][j] != INF) {
                        matrix[i][j] -= rowMin;
                    }
                }
            }
        }

        for (int j = 0; j < n; j++) {
            int colMin = INF;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] < colMin) {
                    colMin = matrix[i][j];
                }
            }
            if (colMin != INF && colMin > 0) {
                reductionCost += colMin;
                for (int i = 0; i < n; i++) {
                    if (matrix[i][j] != INF) {
                        matrix[i][j] -= colMin;
                    }
                }
            }
        }

        return reductionCost;
    }

    public static Node createNewNode(int[][] parentMatrix, List<Integer> path, int level, int i, int j, int n) {
        Node node = new Node(n);
        node.path = new ArrayList<>(path);
        if (level != 0) {
            node.path.add(j);
        }

        for (int r = 0; r < n; r++) {
            for (int c = 0; c < n; c++) {
                node.reducedMatrix[r][c] = parentMatrix[r][c];
            }
        }

        for (int k = 0; level != 0 && k < n; k++) {
            node.reducedMatrix[i][k] = INF;
            node.reducedMatrix[k][j] = INF;
        }
        node.reducedMatrix[j][0] = INF;

        node.level = level;
        node.vertex = j;

        return node;
    }

    public static int solveTSP_BB(int[][] costMatrix, int n) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return Integer.compare(a.cost, b.cost);
            }
        });

        List<Integer> initialPath = new ArrayList<>();
        initialPath.add(0);

        Node root = createNewNode(costMatrix, initialPath, 0, -1, 0, n);
        root.cost = reduceMatrix(root.reducedMatrix, n);
        pq.add(root);

        int minCost = INF;

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();

            if (minNode.cost >= minCost) {
                continue;
            }

            int i = minNode.vertex;

            if (minNode.level == n - 1) {
                if (minNode.cost < minCost) {
                    minCost = minNode.cost;
                }
                continue;
            }

            for (int j = 0; j < n; j++) {
                if (minNode.reducedMatrix[i][j] != INF) {
                    Node child = createNewNode(minNode.reducedMatrix, minNode.path, minNode.level + 1, i, j, n);
                    child.cost = minNode.cost + minNode.reducedMatrix[i][j] + reduceMatrix(child.reducedMatrix, n);

                    if (child.cost < minCost) {
                        pq.add(child);
                    }
                }
            }
        }

        return minCost;
    }

    public static int[][] generateRandomDistanceMatrix(int n, Random random) {
        int[][] matrix = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    matrix[i][j] = INF;
                } else {
                    matrix[i][j] = 10 + random.nextInt(90);
                }
            }
        }
        return matrix;
    }

    public static void main(String[] args) {
        int[] citySizes = {4, 6, 8, 10};
        Random random = new Random(42);

        System.out.println("=========================================================================================");
        System.out.println("            TRAVELING SALESPERSON: DYNAMIC PROGRAMMING VS. BRANCH AND BOUND              ");
        System.out.println("=========================================================================================");
        System.out.printf("%-10s | %-12s | %-12s | %-14s | %-14s | %-8s%n",
                "Cities (N)", "DP Cost", "B&B Cost", "DP Time (ms)", "B&B Time (ms)", "Status");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int n : citySizes) {
            int[][] dist = generateRandomDistanceMatrix(n, random);

            long startDP = System.nanoTime();
            int costDP = solveTSP_DP(dist, n);
            long endDP = System.nanoTime();
            double timeDP = (endDP - startDP) / 1000000.0;

            long startBB = System.nanoTime();
            int costBB = solveTSP_BB(dist, n);
            long endBB = System.nanoTime();
            double timeBB = (endBB - startBB) / 1000000.0;

            String status = (costDP == costBB) ? "PASSED" : "FAILED";

            System.out.printf("%-10d | %-12d | %-12d | %-14.4f | %-14.4f | %-8s%n",
                    n, costDP, costBB, timeDP, timeBB, status);
        }

        System.out.println("=========================================================================================");
    }
}
