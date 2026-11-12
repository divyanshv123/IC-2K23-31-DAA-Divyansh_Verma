import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DepthFirstSearch {

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

        void recursiveDFSUtil(int u, boolean[] visited) {
            visited[u] = true;
            System.out.print("V" + u + " ");

            for (int v : adjList.get(u)) {
                if (!visited[v]) {
                    recursiveDFSUtil(v, visited);
                }
            }
        }

        void performRecursiveDFS(int startVertex) {
            boolean[] visited = new boolean[vertices];
            System.out.print("Recursive DFS Traversal: ");
            recursiveDFSUtil(startVertex, visited);
            System.out.println();
        }

        void performIterativeDFS(int startVertex) {
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();

            stack.push(startVertex);
            System.out.print("Iterative DFS Traversal: ");

            while (!stack.isEmpty()) {
                int u = stack.pop();

                if (!visited[u]) {
                    visited[u] = true;
                    System.out.print("V" + u + " ");

                    List<Integer> neighbors = adjList.get(u);
                    for (int i = neighbors.size() - 1; i >= 0; i--) {
                        int v = neighbors.get(i);
                        if (!visited[v]) {
                            stack.push(v);
                        }
                    }
                }
            }
            System.out.println();
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

        System.out.println("DEPTH FIRST SEARCH (DFS) TRAVERSAL");
        System.out.println("Starting Vertex: V0");
        System.out.println("--------------------------------------------------");

        graph.performRecursiveDFS(0);
        graph.performIterativeDFS(0);

        System.out.println("--------------------------------------------------");
    }
}
