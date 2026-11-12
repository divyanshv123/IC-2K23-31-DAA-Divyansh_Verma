import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class TravelingSalespersonBranchAndBound {

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

    public static void solveTSP(int[][] costMatrix, int n) {
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
        List<Integer> optimalPath = null;
        int exploredNodes = 0;

        while (!pq.isEmpty()) {
            Node minNode = pq.poll();
            exploredNodes++;

            if (minNode.cost >= minCost) {
                continue;
            }

            int i = minNode.vertex;

            if (minNode.level == n - 1) {
                minNode.path.add(0);
                if (minNode.cost < minCost) {
                    minCost = minNode.cost;
                    optimalPath = minNode.path;
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

        System.out.println("TRAVELING SALESPERSON PROBLEM (BRANCH AND BOUND)");
        System.out.println("Number of Cities: " + n);
        System.out.println("-------------------------------------------------");
        System.out.println("Minimum Tour Cost: " + minCost);
        System.out.print("Optimal Tour:      ");
        for (int k = 0; k < optimalPath.size(); k++) {
            System.out.print(optimalPath.get(k) + (k < optimalPath.size() - 1 ? " -> " : ""));
        }
        System.out.println();
        System.out.println("Total State-Space Nodes Explored: " + exploredNodes);
        System.out.println("-------------------------------------------------");
    }

    public static void main(String[] args) {
        int n = 4;
        int[][] costMatrix = {
            {INF, 10, 15, 20},
            {10, INF, 35, 25},
            {15, 35, INF, 30},
            {20, 25, 30, INF}
        };

        solveTSP(costMatrix, n);
    }
}
