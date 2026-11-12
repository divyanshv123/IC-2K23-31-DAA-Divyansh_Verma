import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class KnapsackComparison {

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

    public static int solveDP(int[] values, int[] weights, int capacity) {
        int n = values.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
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

    public static int[] solveBranchAndBound(Item[] items, int capacity) {
        int n = items.length;
        Item[] sortedItems = items.clone();

        Arrays.sort(sortedItems, new Comparator<Item>() {
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
        root.bound = computeBound(root, n, capacity, sortedItems);
        pq.add(root);

        int maxProfit = 0;
        int exploredNodes = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            exploredNodes++;

            if (curr.bound > maxProfit) {
                int nextLevel = curr.level + 1;

                if (nextLevel < n) {
                    Node leftChild = new Node(nextLevel, curr.profit + (int) sortedItems[nextLevel].value, curr.weight + (int) sortedItems[nextLevel].weight);

                    if (leftChild.weight <= capacity && leftChild.profit > maxProfit) {
                        maxProfit = leftChild.profit;
                    }

                    leftChild.bound = computeBound(leftChild, n, capacity, sortedItems);
                    if (leftChild.bound > maxProfit) {
                        pq.add(leftChild);
                    }

                    Node rightChild = new Node(nextLevel, curr.profit, curr.weight);
                    rightChild.bound = computeBound(rightChild, n, capacity, sortedItems);

                    if (rightChild.bound > maxProfit) {
                        pq.add(rightChild);
                    }
                }
            }
        }

        return new int[]{maxProfit, exploredNodes};
    }

    public static void main(String[] args) {
        int[] itemCounts = {10, 15, 20, 24};
        int capacity = 100;
        Random random = new Random(42);

        System.out.println("=========================================================================================");
        System.out.println("                0/1 KNAPSACK: DYNAMIC PROGRAMMING VS. BRANCH AND BOUND                    ");
        System.out.println("=========================================================================================");
        System.out.printf("%-8s | %-10s | %-12s | %-12s | %-12s | %-12s | %-8s%n",
                "Items(N)", "Capacity", "DP Val", "B&B Val", "DP Time(ms)", "B&B Time(ms)", "Status");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int n : itemCounts) {
            int[] values = new int[n];
            int[] weights = new int[n];
            Item[] items = new Item[n];

            for (int i = 0; i < n; i++) {
                values[i] = 10 + random.nextInt(90);
                weights[i] = 5 + random.nextInt(25);
                items[i] = new Item(i + 1, weights[i], values[i]);
            }

            long startDP = System.nanoTime();
            int dpVal = solveDP(values, weights, capacity);
            long endDP = System.nanoTime();
            double dpTime = (endDP - startDP) / 1000000.0;

            long startBB = System.nanoTime();
            int[] bbResult = solveBranchAndBound(items, capacity);
            long endBB = System.nanoTime();
            double bbTime = (endBB - startBB) / 1000000.0;

            String status = (dpVal == bbResult[0]) ? "PASSED" : "FAILED";

            System.out.printf("%-8d | %-10d | %-12d | %-12d | %-12.4f | %-12.4f | %-8s%n",
                    n, capacity, dpVal, bbResult[0], dpTime, bbTime, status);
        }

        System.out.println("=========================================================================================");
    }
}
