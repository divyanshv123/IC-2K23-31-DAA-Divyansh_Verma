public class MultistageGraph {

    public static void findShortestPath(int[][] graph, int vertices) {
        int[] cost = new int[vertices];
        int[] nextVertex = new int[vertices];

        cost[vertices - 1] = 0;
        nextVertex[vertices - 1] = vertices - 1;

        for (int i = vertices - 2; i >= 0; i--) {
            int minCost = Integer.MAX_VALUE;

            for (int j = i + 1; j < vertices; j++) {
                if (graph[i][j] != 0 && cost[j] != Integer.MAX_VALUE) {
                    int currentCost = graph[i][j] + cost[j];
                    if (currentCost < minCost) {
                        minCost = currentCost;
                        nextVertex[i] = j;
                    }
                }
            }

            cost[i] = minCost;
        }

        System.out.println("MULTISTAGE GRAPH SHORTEST PATH (DYNAMIC PROGRAMMING)");
        System.out.println("-----------------------------------------------------");
        System.out.println("Minimum Cost from Source (0) to Sink (" + (vertices - 1) + "): " + cost[0]);

        System.out.print("Optimal Path: 0");
        int current = 0;
        while (current != vertices - 1) {
            current = nextVertex[current];
            System.out.print(" -> " + current);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int vertices = 8;
        int[][] graph = {
            {0, 1, 2, 5, 0, 0, 0, 0},
            {0, 0, 0, 0, 4, 11, 0, 0},
            {0, 0, 0, 0, 9, 5, 16, 0},
            {0, 0, 0, 0, 0, 0, 2, 0},
            {0, 0, 0, 0, 0, 0, 0, 18},
            {0, 0, 0, 0, 0, 0, 0, 13},
            {0, 0, 0, 0, 0, 0, 0, 2},
            {0, 0, 0, 0, 0, 0, 0, 0}
        };

        findShortestPath(graph, vertices);
    }
}
