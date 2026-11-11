public class BellmanFord {

    static class Edge {
        int src;
        int dest;
        int weight;

        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void bellmanFord(Edge[] edges, int vertices, int source) {
        int[] distance = new int[vertices];

        for (int i = 0; i < vertices; i++) {
            distance[i] = Integer.MAX_VALUE;
        }
        distance[source] = 0;

        for (int i = 1; i < vertices; i++) {
            for (int j = 0; j < edges.length; j++) {
                int u = edges[j].src;
                int v = edges[j].dest;
                int weight = edges[j].weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                }
            }
        }

        for (int j = 0; j < edges.length; j++) {
            int u = edges[j].src;
            int v = edges[j].dest;
            int weight = edges[j].weight;

            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Graph contains negative weight cycle! No solution exists.");
                return;
            }
        }

        System.out.println("BELLMAN-FORD SINGLE-SOURCE SHORTEST PATH");
        System.out.println("Source Vertex: " + source);
        System.out.println("----------------------------------------");
        System.out.printf("%-12s | %-15s%n", "Destination", "Shortest Distance");
        System.out.println("----------------------------------------");

        for (int i = 0; i < vertices; i++) {
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.printf("Vertex %-5d | %-15s%n", i, "UNREACHABLE");
            } else {
                System.out.printf("Vertex %-5d | %-15d%n", i, distance[i]);
            }
        }
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        int vertices = 5;
        Edge[] edges = {
            new Edge(0, 1, -1),
            new Edge(0, 2, 4),
            new Edge(1, 2, 3),
            new Edge(1, 3, 2),
            new Edge(1, 4, 2),
            new Edge(3, 2, 5),
            new Edge(3, 1, 1),
            new Edge(4, 3, -3)
        };

        bellmanFord(edges, vertices, 0);
    }
}
