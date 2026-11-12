import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BreadthFirstSearch {

    static class Graph {
        int vertices;
        List<List<Integer>> adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>();
            for (int i = 0; i < vertices; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        void performBFS(int startVertex) {
            boolean[] visited = new boolean[vertices];
            int[] distance = new int[vertices];
            int[] parent = new int[vertices];

            for (int i = 0; i < vertices; i++) {
                distance[i] = -1;
                parent[i] = -1;
            }

            Queue<Integer> queue = new LinkedList<>();

            visited[startVertex] = true;
            distance[startVertex] = 0;
            queue.add(startVertex);

            System.out.println("BREADTH FIRST SEARCH (BFS) TRAVERSAL");
            System.out.println("Starting Vertex: V" + startVertex);
            System.out.println("--------------------------------------------------");
            System.out.print("BFS Traversal Order: ");

            while (!queue.isEmpty()) {
                int u = queue.poll();
                System.out.print("V" + u + " ");

                for (int v : adjList.get(u)) {
                    if (!visited[v]) {
                        visited[v] = true;
                        distance[v] = distance[u] + 1;
                        parent[v] = u;
                        queue.add(v);
                    }
                }
            }

            System.out.println("\n--------------------------------------------------");
            System.out.printf("%-10s | %-16s | %-10s%n", "Vertex", "Shortest Level", "Parent");
            System.out.println("--------------------------------------------------");
            for (int i = 0; i < vertices; i++) {
                String parentStr = (parent[i] == -1) ? "None (Root)" : "V" + parent[i];
                System.out.printf("V%-9d | %-16d | %-10s%n", i, distance[i], parentStr);
            }
            System.out.println("--------------------------------------------------");
        }
    }

    public static void main(String[] args) {
        int vertices = 6;
        Graph graph = new Graph(vertices);

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);

        graph.performBFS(0);
    }
}
