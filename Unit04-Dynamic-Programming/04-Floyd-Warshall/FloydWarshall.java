public class FloydWarshall {

    static final int INF = 99999;

    public static void floydWarshall(int[][] graph, int vertices) {
        int[][] dist = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        for (int k = 0; k < vertices; k++) {
            for (int i = 0; i < vertices; i++) {
                for (int j = 0; j < vertices; j++) {
                    if (dist[i][k] != INF && dist[k][j] != INF && dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        System.out.println("ALL-PAIRS SHORTEST PATHS (FLOYD-WARSHALL ALGORITHM)");
        System.out.println("--------------------------------------------------");
        System.out.println("Shortest Distance Matrix between every pair of vertices:");
        System.out.print("      ");
        for (int i = 0; i < vertices; i++) {
            System.out.printf("%6s", "V" + i);
        }
        System.out.println();
        System.out.println("--------------------------------------------------");

        for (int i = 0; i < vertices; i++) {
            System.out.printf("%4s: ", "V" + i);
            for (int j = 0; j < vertices; j++) {
                if (dist[i][j] == INF) {
                    System.out.printf("%6s", "INF");
                } else {
                    System.out.printf("%6d", dist[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int vertices = 4;
        int[][] graph = {
            {0, 3, INF, 7},
            {8, 0, 2, INF},
            {5, INF, 0, 1},
            {2, INF, INF, 0}
        };

        floydWarshall(graph, vertices);
    }
}
