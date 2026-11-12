import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;

public class BfsDfsComparison {

    static class GraphData {
        int vertices;
        int[][] matrix;
        List<List<Integer>> adjList;

        GraphData(int vertices) {
            this.vertices = vertices;
            this.matrix = new int[vertices][vertices];
            this.adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            matrix[u][v] = 1;
            matrix[v][u] = 1;
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }
    }

    public static int runBFSList(GraphData graph, int start) {
        boolean[] visited = new boolean[graph.vertices];
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            count++;
            for (int v : graph.adjList.get(u)) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return count;
    }

    public static int runBFSMatrix(GraphData graph, int start) {
        boolean[] visited = new boolean[graph.vertices];
        Queue<Integer> queue = new LinkedList<>();
        int count = 0;

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {
            int u = queue.poll();
            count++;
            for (int v = 0; v < graph.vertices; v++) {
                if (graph.matrix[u][v] == 1 && !visited[v]) {
                    visited[v] = true;
                    queue.add(v);
                }
            }
        }
        return count;
    }

    public static int runDFSList(GraphData graph, int start) {
        boolean[] visited = new boolean[graph.vertices];
        return dfsListUtil(graph, start, visited);
    }

    private static int dfsListUtil(GraphData graph, int u, boolean[] visited) {
        visited[u] = true;
        int count = 1;
        for (int v : graph.adjList.get(u)) {
            if (!visited[v]) {
                count += dfsListUtil(graph, v, visited);
            }
        }
        return count;
    }

    public static int runDFSMatrix(GraphData graph, int start) {
        boolean[] visited = new boolean[graph.vertices];
        return dfsMatrixUtil(graph, start, visited);
    }

    private static int dfsMatrixUtil(GraphData graph, int u, boolean[] visited) {
        visited[u] = true;
        int count = 1;
        for (int v = 0; v < graph.vertices; v++) {
            if (graph.matrix[u][v] == 1 && !visited[v]) {
                count += dfsMatrixUtil(graph, v, visited);
            }
        }
        return count;
    }

    public static GraphData generateRandomConnectedGraph(int vertices, double density, Random random) {
        GraphData graph = new GraphData(vertices);
        for (int i = 1; i < vertices; i++) {
            graph.addEdge(i - 1, i);
        }
        for (int i = 0; i < vertices; i++) {
            for (int j = i + 2; j < vertices; j++) {
                if (random.nextDouble() < density) {
                    graph.addEdge(i, j);
                }
            }
        }
        return graph;
    }

    public static void main(String[] args) {
        int[] vertexSizes = {50, 100, 200, 400};
        double edgeDensity = 0.15;
        Random random = new Random(42);

        System.out.println("=========================================================================================");
        System.out.println("                    BFS VS. DFS PERFORMANCE BENCHMARK                                    ");
        System.out.println("          (Comparison of Traversal Paradigms and Graph Representations)                  ");
        System.out.println("=========================================================================================");
        System.out.printf("%-10s | %-16s | %-16s | %-16s | %-16s%n",
                "Vertices", "BFS List (ms)", "DFS List (ms)", "BFS Matrix (ms)", "DFS Matrix (ms)");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int v : vertexSizes) {
            GraphData graph = generateRandomConnectedGraph(v, edgeDensity, random);

            long startBfsList = System.nanoTime();
            int countBfsList = runBFSList(graph, 0);
            long endBfsList = System.nanoTime();
            double timeBfsList = (endBfsList - startBfsList) / 1000000.0;

            long startDfsList = System.nanoTime();
            int countDfsList = runDFSList(graph, 0);
            long endDfsList = System.nanoTime();
            double timeDfsList = (endDfsList - startDfsList) / 1000000.0;

            long startBfsMat = System.nanoTime();
            int countBfsMat = runBFSMatrix(graph, 0);
            long endBfsMat = System.nanoTime();
            double timeBfsMat = (endBfsMat - startBfsMat) / 1000000.0;

            long startDfsMat = System.nanoTime();
            int countDfsMat = runDFSMatrix(graph, 0);
            long endDfsMat = System.nanoTime();
            double timeDfsMat = (endDfsMat - startDfsMat) / 1000000.0;

            System.out.printf("%-10d | %-16.4f | %-16.4f | %-16.4f | %-16.4f%n",
                    v, timeBfsList, timeDfsList, timeBfsMat, timeDfsMat);
        }

        System.out.println("=========================================================================================");
    }
}
