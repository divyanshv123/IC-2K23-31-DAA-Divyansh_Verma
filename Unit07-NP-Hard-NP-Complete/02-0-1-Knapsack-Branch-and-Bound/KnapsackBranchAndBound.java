import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class KnapsackBranchAndBound {

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

    public static void solveKnapsack(Item[] items, int capacity) {
        int n = items.length;

        Arrays.sort(items, new Comparator<Item>() {
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
        root.bound = computeBound(root, n, capacity, items);
        pq.add(root);

        int maxProfit = 0;
        int exploredNodes = 0;

        while (!pq.isEmpty()) {
            Node curr = pq.poll();
            exploredNodes++;

            if (curr.bound > maxProfit) {
                int nextLevel = curr.level + 1;

                if (nextLevel < n) {
                    Node leftChild = new Node(nextLevel, curr.profit + (int) items[nextLevel].value, curr.weight + (int) items[nextLevel].weight);

                    if (leftChild.weight <= capacity && leftChild.profit > maxProfit) {
                        maxProfit = leftChild.profit;
                    }

                    leftChild.bound = computeBound(leftChild, n, capacity, items);
                    if (leftChild.bound > maxProfit) {
                        pq.add(leftChild);
                    }

                    Node rightChild = new Node(nextLevel, curr.profit, curr.weight);
                    rightChild.bound = computeBound(rightChild, n, capacity, items);

                    if (rightChild.bound > maxProfit) {
                        pq.add(rightChild);
                    }
                }
            }
        }

        System.out.println("0/1 KNAPSACK PROBLEM (BRANCH AND BOUND - LC SEARCH)");
        System.out.println("Total Items: " + n + ", Knapsack Capacity: " + capacity);
        System.out.println("---------------------------------------------------");
        System.out.println("Maximum Profit Achieved: " + maxProfit);
        System.out.println("Total State-Space Nodes Explored: " + exploredNodes);
        System.out.println("---------------------------------------------------");
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(1, 2, 40),
            new Item(2, 3.14, 50),
            new Item(3, 1.98, 100),
            new Item(4, 5, 95),
            new Item(5, 3, 30)
        };

        int capacity = 10;
        solveKnapsack(items, capacity);
    }
}
