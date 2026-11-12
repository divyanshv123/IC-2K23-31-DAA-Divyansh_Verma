public class GraphColoring {

    public static boolean isSafe(int vertex, int[][] graph, int[] color, int c, int vertices) {
        for (int i = 0; i < vertices; i++) {
            if (graph[vertex][i] == 1 && color[i] == c) {
                return false;
            }
        }
        return true;
    }

    public static boolean graphColoringUtil(int[][] graph, int m, int[] color, int vertex, int vertices) {
        if (vertex == vertices) {
            return true;
        }

        for (int c = 1; c <= m; c++) {
            if (isSafe(vertex, graph, color, c, vertices)) {
                color[vertex] = c;

                if (graphColoringUtil(graph, m, color, vertex + 1, vertices)) {
                    return true;
                }

                color[vertex] = 0;
            }
        }

        return false;
    }

    public static void solveGraphColoring(int[][] graph, int m, int vertices) {
        int[] color = new int[vertices];

        System.out.println("GRAPH COLORING PROBLEM (M-COLORABILITY)");
        System.out.println("Number of Vertices: " + vertices);
        System.out.println("Available Colors (m): " + m);
        System.out.println("----------------------------------------");

        if (!graphColoringUtil(graph, m, color, 0, vertices)) {
            System.out.println("Solution does NOT exist with " + m + " colors.");
            return;
        }

        String[] colorNames = {"None", "Red", "Green", "Blue", "Yellow"};
        System.out.println("Valid Color Assignment Found:");
        System.out.printf("%-10s | %-12s | %-12s%n", "Vertex", "Color ID", "Color Name");
        System.out.println("----------------------------------------");
        for (int i = 0; i < vertices; i++) {
            String name = (color[i] < colorNames.length) ? colorNames[color[i]] : "Color-" + color[i];
            System.out.printf("V%-9d | %-12d | %-12s%n", i, color[i], name);
        }
        System.out.println("----------------------------------------");
    }

    public static void main(String[] args) {
        int vertices = 4;
        int[][] graph = {
            {0, 1, 1, 1},
            {1, 0, 1, 0},
            {1, 1, 0, 1},
            {1, 0, 1, 0}
        };

        int m = 3;
        solveGraphColoring(graph, m, vertices);
    }
}
