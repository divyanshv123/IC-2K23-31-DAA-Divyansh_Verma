import java.util.*;

public class BreadthFirstSearch {

    static final int SIZE = 6;

    static ArrayList<Integer>[] network = new ArrayList[SIZE];

    // Create a connection between two vertices
    public static void connect(int first, int second) {
        network[first].add(second);
        network[second].add(first);
    }

    public static void traverseBFS(int source) {

        boolean[] marked = new boolean[SIZE];
        int[] level = new int[SIZE];
        int[] previous = new int[SIZE];

        Arrays.fill(level, -1);
        Arrays.fill(previous, -1);

        Queue<Integer> pending = new LinkedList<>();

        marked[source] = true;
        level[source] = 0;
        pending.add(source);

        System.out.print("\nBFS Traversal: ");

        while (!pending.isEmpty()) {

            int current = pending.poll();
            System.out.print(current + " ");

            for (int neighbour : network[current]) {

                if (!marked[neighbour]) {
                    marked[neighbour] = true;
                    level[neighbour] = level[current] + 1;
                    previous[neighbour] = current;

                    pending.add(neighbour);
                }
            }
        }

        System.out.println("\n");

        System.out.println("Vertex Details");
        System.out.println("--------------------------------");
        System.out.printf("%-12s %-18s %-10s%n",
                          "Vertex", "Distance", "Parent");

        for (int i = 0; i < SIZE; i++) {
            System.out.printf("%-12d %-18d %-10d%n",
                              i, level[i], previous[i]);
        }
    }

    public static void main(String[] args) {

        // Initialize adjacency lists
        for (int i = 0; i < SIZE; i++) {
            network[i] = new ArrayList<>();
        }

        // Add edges to the graph
        connect(0, 1);
        connect(0, 2);
        connect(1, 3);
        connect(1, 4);
        connect(2, 4);
        connect(3, 5);
        connect(4, 5);

        int startingVertex = 0;

        traverseBFS(startingVertex);
    }
}
