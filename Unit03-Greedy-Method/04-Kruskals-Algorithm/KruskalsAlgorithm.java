import java.util.Arrays;
import java.util.Scanner;

class Link implements Comparable<Link> {
    int start;
    int end;
    int cost;

    Link(int start, int end, int cost) {
        this.start = start;
        this.end = end;
        this.cost = cost;
    }

    public int compareTo(Link other) {
        return Integer.compare(this.cost, other.cost);
    }
}

public class KruskalsAlgorithm {

    static int getParent(int[] parent, int vertex) {
        if (parent[vertex] != vertex) {
            parent[vertex] = getParent(parent, parent[vertex]);
        }

        return parent[vertex];
    }

    static void joinSets(int[] parent, int[] rank, int a, int b) {

        int rootA = getParent(parent, a);
        int rootB = getParent(parent, b);

        if (rootA == rootB) {
            return;
        }

        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }

    static void findMST(Link[] links, int vertexCount) {

        Arrays.sort(links);

        int[] parent = new int[vertexCount];
        int[] rank = new int[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            parent[i] = i;
        }

        int totalCost = 0;
        int count = 0;

        System.out.println("\n========== MST RESULT ==========");
        System.out.printf("%-12s %-12s %-12s%n",
                "From", "To", "Weight");
        System.out.println("--------------------------------");

        for (Link link : links) {

            int first = getParent(parent, link.start);
            int second = getParent(parent, link.end);

            if (first != second) {

                System.out.printf("%-12d %-12d %-12d%n",
                        link.start, link.end, link.cost);

                joinSets(parent, rank, link.start, link.end);

                totalCost += link.cost;
                count++;

                if (count == vertexCount - 1) {
                    break;
                }
            }
        }

        System.out.println("--------------------------------");

        if (count == vertexCount - 1) {
            System.out.println("Selected edges : " + count);
            System.out.println("Minimum cost   : " + totalCost);
            System.out.println("Result         : MST completed successfully");
        } else {
            System.out.println("Result : MST cannot be formed.");
            System.out.println("Reason : The graph is disconnected.");
        }

        System.out.println("================================");
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.println("================================");
        System.out.println("       KRUSKAL'S ALGORITHM");
        System.out.println("================================");

        System.out.print("Enter total number of vertices: ");
        int vertexCount = input.nextInt();

        if (vertexCount <= 0) {
            System.out.println("Invalid number of vertices.");
            input.close();
            return;
        }

        System.out.print("Enter total number of edges: ");
        int edgeCount = input.nextInt();

        if (edgeCount < 0) {
            System.out.println("Invalid number of edges.");
            input.close();
            return;
        }

        Link[] links = new Link[edgeCount];

        System.out.println("\nProvide edge details (Start End Weight):");

        for (int i = 0; i < edgeCount; i++) {

            System.out.print("Enter details for edge " + (i + 1) + ": ");

            int start = input.nextInt();
            int end = input.nextInt();
            int cost = input.nextInt();

            if (start < 0 || start >= vertexCount ||
                    end < 0 || end >= vertexCount) {
                System.out.println("Invalid vertex number.");
                input.close();
                return;
            }

            links[i] = new Link(start, end, cost);
        }

        findMST(links, vertexCount);

        input.close();
    }
}
