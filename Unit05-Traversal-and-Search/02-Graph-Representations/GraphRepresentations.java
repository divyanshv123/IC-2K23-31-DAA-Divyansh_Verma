import java.util.ArrayList;
import java.util.List;

public class GraphRepresentations {

    static class AdjacencyMatrixGraph {
        int vertices;
        int[][] matrix;

        AdjacencyMatrixGraph(int vertices) {
            this.vertices = vertices;
            this.matrix = new int[vertices][vertices];
        }

        void addEdge(int u, int v) {
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        void printMatrix() {
            System.out.println("Adjacency Matrix Representation:");
            System.out.print("    ");
            for (int i = 0; i < vertices; i++) {
                System.out.printf("%4s", "V" + i);
            }
            System.out.println();
            System.out.println("    " + "----".repeat(vertices));

            for (int i = 0; i < vertices; i++) {
                System.out.printf("%3s:", "V" + i);
                for (int j = 0; j < vertices; j++) {
                    System.out.printf("%4d", matrix[i][j]);
                }
                System.out.println();
            }
        }
    }

    static class AdjacencyListGraph {
        int vertices;
        List<List<Integer>> adjacencyList;

        AdjacencyListGraph(int vertices) {
            this.vertices = vertices;
            this.adjacencyList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                this.adjacencyList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adjacencyList.get(u).add(v);
            adjacencyList.get(v).add(u);
        }

        void printList() {
            System.out.println("Adjacency List Representation:");
            for (int i = 0; i < vertices; i++) {
                System.out.print("Vertex V" + i + " -> ");
                List<Integer> neighbors = adjacencyList.get(i);
                for (int j = 0; j < neighbors.size(); j++) {
                    System.out.print("V" + neighbors.get(j) + (j < neighbors.size() - 1 ? " -> " : ""));
                }
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        int vertices = 5;
        int[][] edges = {
            {0, 1},
            {0, 4},
            {1, 2},
            {1, 3},
            {1, 4},
            {2, 3},
            {3, 4}
        };

        System.out.println("GRAPH REPRESENTATIONS: ADJACENCY MATRIX VS. ADJACENCY LIST");
        System.out.println("Total Vertices: " + vertices + ", Total Edges: " + edges.length);
        System.out.println("==========================================================");

        AdjacencyMatrixGraph matrixGraph = new AdjacencyMatrixGraph(vertices);
        for (int i = 0; i < edges.length; i++) {
            matrixGraph.addEdge(edges[i][0], edges[i][1]);
        }
        matrixGraph.printMatrix();

        System.out.println("\n----------------------------------------------------------\n");

        AdjacencyListGraph listGraph = new AdjacencyListGraph(vertices);
        for (int i = 0; i < edges.length; i++) {
            listGraph.addEdge(edges[i][0], edges[i][1]);
        }
        listGraph.printList();

        System.out.println("==========================================================");
    }
}
