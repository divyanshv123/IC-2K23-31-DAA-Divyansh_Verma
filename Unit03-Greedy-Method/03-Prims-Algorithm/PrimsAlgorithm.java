public class PrimsAlgorithm {

    public static int findMinKeyVertex(int[] key, boolean[] includedInMST, int vertices) {
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for (int v = 0; v < vertices; v++) {
            if (!includedInMST[v] && key[v] < min) {
                min = key[v];
                minIndex = v;
            }
        }

        return minIndex;
    }

    public static void primMST(int[][] graph, int vertices) {
        int[] parent = new int[vertices];
        int[] key = new int[vertices];
        boolean[] includedInMST = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            includedInMST[i] = false;
        }

        key[0] = 0;
        parent[0] = -1;

        for (int count = 0; count < vertices - 1; count++) {
            int u = findMinKeyVertex(key, includedInMST, vertices);
            includedInMST[u] = true;

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !includedInMST[v] && graph[u][v] < key[v]) {
                    parent[v] = u;
                    key[v] = graph[u][v];
                }
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree (MST):");
        System.out.printf("%-10s | %-8s%n", "Edge", "Weight");
        System.out.println("---------------------");

        int totalWeight = 0;
        for (int i = 1; i < vertices; i++) {
            System.out.printf("%d - %-7d | %-8d%n", parent[i], i, graph[i][parent[i]]);
            totalWeight += graph[i][parent[i]];
        }

        System.out.println("---------------------");
        System.out.println("Total Minimum Cost: " + totalWeight);
    }

    public static void main(String[] args) {
        int vertices = 5;
        int[][] graph = {
            {0, 2, 0, 6, 0},
            {2, 0, 3, 8, 5},
            {0, 3, 0, 0, 7},
            {6, 8, 0, 0, 9},
            {0, 5, 7, 9, 0}
        };

        System.out.println("PRIM'S MINIMUM SPANNING TREE ALGORITHM");
        System.out.println("Number of Vertices: " + vertices);
        System.out.println("--------------------------------------");

        primMST(graph, vertices);
    }
}
