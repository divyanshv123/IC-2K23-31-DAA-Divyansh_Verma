import java.util.Arrays;
import java.util.Comparator;

public class KruskalsAlgorithm {

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

    public static void kruskalMST(Edge[] edges, int vertices) {
        Arrays.sort(edges, new Comparator<Edge>() {
            public int compare(Edge a, Edge b) {
                return a.weight - b.weight;
            }
        });

        DisjointSet ds = new DisjointSet(vertices);
        Edge[] mst = new Edge[vertices - 1];
        int edgeCount = 0;
        int totalWeight = 0;

        for (int i = 0; i < edges.length && edgeCount < vertices - 1; i++) {
            Edge currentEdge = edges[i];

            if (ds.union(currentEdge.source, currentEdge.destination)) {
                mst[edgeCount++] = currentEdge;
                totalWeight += currentEdge.weight;
            }
        }

        System.out.println("Edges in the Minimum Spanning Tree (MST):");
        System.out.printf("%-10s | %-8s%n", "Edge", "Weight");
        System.out.println("---------------------");

        for (int i = 0; i < edgeCount; i++) {
            System.out.printf("%d - %-7d | %-8d%n", mst[i].source, mst[i].destination, mst[i].weight);
        }

        System.out.println("---------------------");
        System.out.println("Total Minimum Cost: " + totalWeight);
    }

    public static void main(String[] args) {
        int vertices = 5;
        Edge[] edges = {
            new Edge(0, 1, 2),
            new Edge(0, 3, 6),
            new Edge(1, 2, 3),
            new Edge(1, 3, 8),
            new Edge(1, 4, 5),
            new Edge(2, 4, 7),
            new Edge(3, 4, 9)
        };

        System.out.println("KRUSKAL'S MINIMUM SPANNING TREE ALGORITHM");
        System.out.println("Number of Vertices: " + vertices);
        System.out.println("Number of Edges:    " + edges.length);
        System.out.println("-----------------------------------------");

        kruskalMST(edges, vertices);
    }
}
