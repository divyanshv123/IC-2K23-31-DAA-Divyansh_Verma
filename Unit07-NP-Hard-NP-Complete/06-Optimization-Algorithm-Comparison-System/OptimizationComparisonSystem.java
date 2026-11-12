import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class OptimizationComparisonSystem {

    static class Item {
        int id;
        double weight;
        double value;
        double cost;

        Item(int id, double weight, double value) {
            this.id = id;
            this.weight = weight;
            this.value = value;
            this.cost = value / weight;
        }
    }

    static class Node {
        int level;
        int profit;
        int weight;
        double bound;

        Node(int level, int profit, int weight) {
            this.level = level;
            this.profit = profit;
            this.weight = weight;
            this.bound = 0.0;
        }
    }

    public static int runExhaustive(int[] values, int[] weights, int capacity, long[] nodeCounter) {
        int n = values.length;
        int total = 1 << n;
        int maxVal = 0;

        for (int mask = 0; mask < total; mask++) {
            nodeCounter[0]++;
            int curW = 0;
            int curV = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    curW += weights[i];
                    curV += values[i];
                }
            }
            if (curW <= capacity && curV > maxVal) {
                maxVal = curV;
            }
        }
        return maxVal;
    }

    public static int runDP(int[] values, int[] weights, int capacity, long[] nodeCounter) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                nodeCounter[0]++;
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(values[i - 1] + dp[i - 1][w - weights[i - 1]], dp[i - 1][w]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        return dp[n][capacity];
    }

    public static double computeBound(Node u, int n, int capacity, Item[] items) {
        if (u.weight >= capacity) {
            return 0;
        }
        double profitBound = u.profit;
        int j = u.level + 1;
        double totalWeight = u.weight;

        while (j < n && totalWeight + items[j].weight <= capacity) {
            totalWeight += items[j].weight;
            profitBound += items[j].value;
            j++;
        }
        if (j < n) {
            profitBound += (capacity - totalWeight) * items[j].cost;
        }
        return profitBound;
    }

    public static int runBranchAndBound(Item[] items, int capacity, long[] nodeCounter) {
        int n = items.length;
        Item[] sorted = items.clone();
        Arrays.sort(sorted, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return Double.compare(b.cost, a.cost);
            }
        });

        PriorityQueue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            public int compare(Node a, Node b) {
                return Double.compare(b.bound, a.bound);
            }
        });

        Node root = new Node(-1, 0, 0);
        root.bound = computeBound(root, n, capacity, sorted);
        pq.add(root);

        int maxProfit = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            nodeCounter[0]++;

            if (curr.bound > maxProfit) {
                int nextLevel = curr.level + 1;
                if (nextLevel < n) {
                    Node left = new Node(nextLevel, curr.profit + (int) sorted[nextLevel].value, curr.weight + (int) sorted[nextLevel].weight);
                    if (left.weight <= capacity && left.profit > maxProfit) {
                        maxProfit = left.profit;
                    }
                    left.bound = computeBound(left, n, capacity, sorted);
                    if (left.bound > maxProfit) {
                        pq.add(left);
                    }

                    Node right = new Node(nextLevel, curr.profit, curr.weight);
                    right.bound = computeBound(right, n, capacity, sorted);
                    if (right.bound > maxProfit) {
                        pq.add(right);
                    }
                }
            }
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] itemCounts = {8, 12, 16, 20};
        int capacity = 80;

        System.out.println("=========================================================================================================");
        System.out.println("                         OPTIMIZATION ALGORITHM COMPARISON SYSTEM                                ");
        System.out.println("               (Comprehensive Benchmark: Exhaustive vs. DP vs. Branch & Bound)                   ");
        System.out.println("=========================================================================================================");
        System.out.printf("%-6s | %-16s | %-16s | %-16s | %-14s | %-14s | %-10s%n",
                "N", "Strategy", "Explored States", "Optimal Value", "Time (ms)", "Est. Space", "Quality");
        System.out.println("---------------------------------------------------------------------------------------------------------");

        for (int n : itemCounts) {
            int[] values = new int[n];
            int[] weights = new int[n];
            Item[] items = new Item[n];

            for (int i = 0; i < n; i++) {
                values[i] = 15 + (i * 7) % 50;
                weights[i] = 5 + (i * 3) % 20;
                items[i] = new Item(i + 1, weights[i], values[i]);
            }

            long[] exhNodes = new long[1];
            long startExh = System.nanoTime();
            int valExh = (n <= 16) ? runExhaustive(values, weights, capacity, exhNodes) : -1;
            long endExh = System.nanoTime();
            double timeExh = (endExh - startExh) / 1000000.0;

            long[] dpNodes = new long[1];
            long startDP = System.nanoTime();
            int valDP = runDP(values, weights, capacity, dpNodes);
            long endDP = System.nanoTime();
            double timeDP = (endDP - startDP) / 1000000.0;

            long[] bbNodes = new long[1];
            long startBB = System.nanoTime();
            int valBB = runBranchAndBound(items, capacity, bbNodes);
            long endBB = System.nanoTime();
            double timeBB = (endBB - startBB) / 1000000.0;

            if (n <= 16) {
                System.out.printf("%-6d | %-16s | %-16d | %-16d | %-14.4f | %-14s | %-10s%n",
                        n, "Exhaustive", exhNodes[0], valExh, timeExh, "O(1)", "Exact");
            } else {
                System.out.printf("%-6d | %-16s | %-16s | %-16s | %-14s | %-14s | %-10s%n",
                        n, "Exhaustive", "Skipped (>10^6)", "N/A", "Timeout", "O(1)", "Exact");
            }

            System.out.printf("%-6d | %-16s | %-16d | %-16d | %-14.4f | %-14s | %-10s%n",
                    n, "Dynamic Prog", dpNodes[0], valDP, timeDP, "O(n*W)", "Exact");
            System.out.printf("%-6d | %-16s | %-16d | %-16d | %-14.4f | %-14s | %-10s%n",
                    n, "Branch & Bound", bbNodes[0], valBB, timeBB, "O(LiveNodes)", "Exact");
            System.out.println("---------------------------------------------------------------------------------------------------------");
        }

        System.out.println("=========================================================================================================");
    }
}
