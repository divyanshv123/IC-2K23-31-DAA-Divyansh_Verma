import java.util.*;

public class TravelingSalespersonBranchAndBound {

    static int cityCount;
    static int[][] travelCost;
    static boolean[] visited;

    static int minimumCost = Integer.MAX_VALUE;
    static int[] bestRoute;
    static int[] currentRoute;

    // Find the minimum cost using Branch and Bound
    public static void findTour(int currentCity, int level, int totalCost) {

        // All cities have been visited
        if (level == cityCount) {

            int finalCost = totalCost + travelCost[currentCity][0];

            // Update the best route
            if (finalCost < minimumCost) {

                minimumCost = finalCost;

                for (int i = 0; i < cityCount; i++) {
                    bestRoute[i] = currentRoute[i];
                }
            }

            return;
        }

        // Try visiting every unvisited city
        for (int nextCity = 1; nextCity < cityCount; nextCity++) {

            if (!visited[nextCity]) {

                int newCost = totalCost + travelCost[currentCity][nextCity];

                // Bound: stop exploring if current cost is already too high
                if (newCost >= minimumCost) {
                    continue;
                }

                visited[nextCity] = true;
                currentRoute[level] = nextCity;

                findTour(nextCity, level + 1, newCost);

                // Backtrack
                visited[nextCity] = false;
            }
        }
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter total number of cities: ");
        cityCount = input.nextInt();

        if (cityCount < 2 || cityCount > 15) {
            System.out.println("Please enter between 2 and 15 cities.");
            input.close();
            return;
        }

        travelCost = new int[cityCount][cityCount];

        System.out.println("Enter the travel cost matrix:");

        for (int i = 0; i < cityCount; i++) {
            for (int j = 0; j < cityCount; j++) {
                travelCost[i][j] = input.nextInt();

                if (travelCost[i][j] < 0) {
                    System.out.println("Negative costs are not supported.");
                    input.close();
                    return;
                }
            }
        }

        visited = new boolean[cityCount];
        bestRoute = new int[cityCount];
        currentRoute = new int[cityCount];

        // Start the journey from City 1
        visited[0] = true;
        currentRoute[0] = 0;

        findTour(0, 1, 0);

        System.out.println("\nTravelling Salesman Result");
        System.out.println("------------------------------");

        if (minimumCost == Integer.MAX_VALUE) {
            System.out.println("No valid tour found.");
        } else {

            System.out.print("Best Route: ");

            for (int i = 0; i < cityCount; i++) {
                System.out.print("City " + (bestRoute[i] + 1) + " -> ");
            }

            System.out.println("City 1");
            System.out.println("Minimum Travelling Cost: " + minimumCost);
        }

        input.close();
    }
}
