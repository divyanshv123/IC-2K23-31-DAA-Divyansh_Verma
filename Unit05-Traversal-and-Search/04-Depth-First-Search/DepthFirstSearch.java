import java.util.*;

public class DepthFirstSearch {

    static final int TOTAL = 8;

    static ArrayList<Integer>[] graph = new ArrayList[TOTAL];
    static boolean[] visited = new boolean[TOTAL];

    // Add an undirected edge
    public static void createLink(int a, int b) {
        graph[a].add(b);
        graph[b].add(a);
    }

    // Reset visited status
    public static void clearVisited() {
        Arrays.fill(visited, false);
    }

    // Recursive DFS traversal
    public static void recursiveSearch(int vertex) {

        visited[vertex] = true;
        System.out.print(vertex + " ");

        for (int next : graph[vertex]) {
            if (!visited[next]) {
                recursiveSearch(next);
            }
        }
    }

    // Iterative DFS traversal using a stack
    public static void iterativeSearch(int start) {

        Stack<Integer> stack = new Stack<>();
        stack.push(start);

        while (!stack.isEmpty()) {

            int vertex = stack.pop();

            if (visited[vertex]) {
                continue;
            }

            visited[vertex] = true;
            System.out.print(vertex + " ");

            // Reverse order maintains the recursive traversal order
            for (int i = graph[vertex].size() - 1; i >= 0; i--) {

                int next = graph[vertex].get(i);

                if (!visited[next]) {
                    stack.push(next);
                }
            }
        }
    }

    public static void main(String[] args) {

        // Initialize adjacency lists
        for (int i = 0; i < TOTAL; i++) {
            graph[i] = new ArrayList<>();
        }

        // Create graph connections
        createLink(0, 1);
        createLink(0, 2);
        createLink(1, 3);
        createLink(1, 4);
        createLink(2, 4);
        createLink(3, 5);
        createLink(4, 5);
        createLink(6, 7);

        // Recursive DFS
        clearVisited();

        System.out.print("DFS using Recursion: ");
        recursiveSearch(0);
        System.out.println();

        // Iterative DFS
        clearVisited();

        System.out.print("DFS using Stack: ");
        iterativeSearch(0);
        System.out.println();

        // Traverse every connected component
        clearVisited();

        System.out.println("\nTraversal of all graph components:");

        int groupCount = 0;

        for (int i = 0; i < TOTAL; i++) {

            if (!visited[i]) {
                groupCount++;

                System.out.print("Component " + groupCount + ": ");
                recursiveSearch(i);
                System.out.println();
            }
        }
    }
}
