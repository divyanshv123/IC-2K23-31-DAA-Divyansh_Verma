import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class NetworkDesignComparison {

    static class Edge {
        int source;
        int destination;
        int weight;

        Edge(int source, int destination, int weight) {
            this.source = source;
            this.destination = destination;
            this.weight = weight;
        }
    }

    static class DisjointSet {
        int[] parent;
        int[] rank;

        DisjointSet(int size) {
            parent = new int[size];
            rank = new int[size];
            for (int i = 0; i < size; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }

        int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }

        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);

            if (rootX == rootY) {
                return false;
            }

            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }

            return true;
        }
    }

    public static int runPrims(int[][] graph, int vertices) {
        int[] key = new int[vertices];
        boolean[] includedInMST = new boolean[vertices];

        for (int i = 0; i < vertices; i++) {
            key[i] = Integer.MAX_VALUE;
            includedInMST[i] = false;
        }

        key[0] = 0;
        int totalCost = 0;

        for (int count = 0; count < vertices; count++) {
            int minKey = Integer.MAX_VALUE;
            int u = -1;

            for (int v = 0; v < vertices; v++) {
                if (!includedInMST[v] && key[v] < minKey) {
                    minKey = key[v];
                    u = v;
                }
            }

            if (u == -1) {
                break;
            }

            includedInMST[u] = true;
            totalCost += key[u];

            for (int v = 0; v < vertices; v++) {
                if (graph[u][v] != 0 && !includedInMST[v] && graph[u][v] < key[v]) {
                    key[v] = graph[u][v];
                }
            }
        }

        return totalCost;
    }

    public static int runKruskals(List<Edge> edgeList, int vertices) {
        Edge[] edges = edgeList.toArray(new Edge[0]);

        Arrays.sort(edges, new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                return a.weight - b.weight;
            }
        });

        DisjointSet ds = new DisjointSet(vertices);
        int totalCost = 0;
        int edgesCount = 0;

        for (int i = 0; i < edges.length && edgesCount < vertices - 1; i++) {
            Edge edge = edges[i];
            if (ds.union(edge.source, edge.destination)) {
                totalCost += edge.weight;
                edgesCount++;
            }
        }

        return totalCost;
    }

    public static void generateConnectedGraph(int vertices, double density, int[][] matrix, List<Edge> edgeList, Random random) {
        for (int i = 1; i < vertices; i++) {
            int weight = 1 + random.nextInt(100);
            matrix[i - 1][i] = weight;
            matrix[i][i - 1] = weight;
            edgeList.add(new Edge(i - 1, i, weight));
        }

        for (int i = 0; i < vertices; i++) {
            for (int j = i + 2; j < vertices; j++) {
                if (random.nextDouble() < density) {
                    int weight = 1 + random.nextInt(100);
                    matrix[i][j] = weight;
                    matrix[j][i] = weight;
                    edgeList.add(new Edge(i, j, weight));
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] vertexSizes = {20, 50, 100, 200};
        double edgeDensity = 0.3;
        Random random = new Random(12345);

        System.out.println("=========================================================================================");
        System.out.println("                     MINIMUM-COST NETWORK DESIGN SYSTEM                                  ");
        System.out.println("                (Comparative Evaluation: Prim's vs Kruskal's MST)                        ");
        System.out.println("=========================================================================================");
        System.out.printf("%-10s | %-10s | %-12s | %-14s | %-14s | %-14s | %-8s%n",
                "Nodes (V)", "Edges (E)", "Prim's Cost", "Kruskal's Cost", "Prim's Time(ms)", "Krusk Time(ms)", "Match");
        System.out.println("-----------------------------------------------------------------------------------------");

        for (int v : vertexSizes) {
            int[][] matrix = new int[v][v];
            List<Edge> edgeList = new ArrayList<>();

            generateConnectedGraph(v, edgeDensity, matrix, edgeList, random);
            int totalEdges = edgeList.size();

            long startPrim = System.nanoTime();
            int primCost = runPrims(matrix, v);
            long endPrim = System.nanoTime();
            double primTime = (endPrim - startPrim) / 1000000.0;

            long startKruskal = System.nanoTime();
            int kruskalCost = runKruskals(edgeList, v);
            long endKruskal = System.nanoTime();
            double kruskalTime = (endKruskal - startKruskal) / 1000000.0;

            String matchStatus = (primCost == kruskalCost) ? "PASSED" : "FAILED";

            System.out.printf("%-10d | %-10d | %-12d | %-14d | %-14.3f | %-14.3f | %-8s%n",
                    v, totalEdges, primCost, kruskalCost, primTime, kruskalTime, matchStatus);
        }

        System.out.println("=========================================================================================");
    }
}
