import java.util.Scanner;

public class FloydWarshall {

    static final int INF = 99999;

    public static void findShortestPaths(int[][] matrix, int vertices) {

        int[][] shortest = new int[vertices][vertices];

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                shortest[i][j] = matrix[i][j];
            }
        }

        for (int via = 0; via < vertices; via++) {
            for (int from = 0; from < vertices; from++) {
                for (int to = 0; to < vertices; to++) {

                    if (shortest[from][via] != INF &&
                        shortest[via][to] != INF) {

                        int newDistance =
                            shortest[from][via] + shortest[via][to];

                        if (newDistance < shortest[from][to]) {
                            shortest[from][to] = newDistance;
                        }
                    }
                }
            }
        }

        System.out.println("\nShortest Distance Between Every Pair of Vertices");
        System.out.println("-----------------------------------------------");

        System.out.print("     ");
        for (int i = 0; i < vertices; i++) {
            System.out.printf("%6d", i);
        }
        System.out.println();

        for (int i = 0; i < vertices; i++) {
            System.out.printf("%4d ", i);

            for (int j = 0; j < vertices; j++) {
                if (shortest[i][j] == INF) {
                    System.out.printf("%6s", "INF");
                } else {
                    System.out.printf("%6d", shortest[i][j]);
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter total number of vertices: ");
        int vertices = input.nextInt();

        if (vertices <= 0) {
            System.out.println("Number of vertices must be positive.");
            input.close();
            return;
        }

        int[][] matrix = new int[vertices][vertices];

        System.out.println("Enter the adjacency matrix:");
        System.out.println("Use 99999 if there is no direct connection.");

        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                matrix[i][j] = input.nextInt();
            }
        }

        findShortestPaths(matrix, vertices);

        input.close();
    }
}
