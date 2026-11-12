import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NetworkTraversalSystem {

    static class NetworkGraph {
        int nodeCount;
        String[] nodeNames;
        List<List<Integer>> adjList;

        NetworkGraph(String[] nodeNames) {
            this.nodeCount = nodeNames.length;
            this.nodeNames = nodeNames;
            this.adjList = new ArrayList<>();
            for (int i = 0; i < nodeCount; i++) {
                adjList.add(new ArrayList<>());
            }
        }

        void addConnection(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        void findRoutingPath(int start, int target) {
            boolean[] visited = new boolean[nodeCount];
            int[] parent = new int[nodeCount];
            for (int i = 0; i < nodeCount; i++) {
                parent[i] = -1;
            }

            Queue<Integer> queue = new LinkedList<>();
            visited[start] = true;
            queue.add(start);

            boolean reached = false;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                if (u == target) {
                    reached = true;
                    break;
                }

                for (int v : adjList.get(u)) {
                    if (!visited[v]) {
                        visited[v] = true;
                        parent[v] = u;
                        queue.add(v);
                    }
                }
            }

            if (!reached) {
                System.out.println("No route found between " + nodeNames[start] + " and " + nodeNames[target]);
                return;
            }

            List<Integer> path = new ArrayList<>();
            int curr = target;
            while (curr != -1) {
                path.add(curr);
                curr = parent[curr];
            }
            Collections.reverse(path);

            System.out.println("Optimal Routing Path from " + nodeNames[start] + " to " + nodeNames[target] + ":");
            for (int i = 0; i < path.size(); i++) {
                System.out.print(nodeNames[path.get(i)] + (i < path.size() - 1 ? " -> " : ""));
            }
            System.out.println("\nTotal Network Hops: " + (path.size() - 1));
        }

        void analyzeNetworkConnectivity() {
            boolean[] visited = new boolean[nodeCount];
            int componentCount = 0;

            System.out.println("Network Connected Components Analysis:");
            for (int i = 0; i < nodeCount; i++) {
                if (!visited[i]) {
                    componentCount++;
                    System.out.print("  Subnet Component " + componentCount + ": [ ");
                    dfsComponent(i, visited);
                    System.out.println("]");
                }
            }
            System.out.println("Total Independent Subnets: " + componentCount);
        }

        private void dfsComponent(int u, boolean[] visited) {
            visited[u] = true;
            System.out.print(nodeNames[u] + " ");
            for (int v : adjList.get(u)) {
                if (!visited[v]) {
                    dfsComponent(v, visited);
                }
            }
        }
    }

    public static void main(String[] args) {
        String[] nodes = {
            "Gateway-Router",
            "Core-Switch-A",
            "Core-Switch-B",
            "App-Server-1",
            "Database-Server",
            "Backup-Storage",
            "Isolated-Test-Node"
        };

        NetworkGraph network = new NetworkGraph(nodes);

        network.addConnection(0, 1);
        network.addConnection(0, 2);
        network.addConnection(1, 3);
        network.addConnection(1, 4);
        network.addConnection(2, 4);
        network.addConnection(3, 5);
        network.addConnection(4, 5);

        System.out.println("==========================================================================");
        System.out.println("                NETWORK TRAVERSAL & EXPLORATION SYSTEM                    ");
        System.out.println("==========================================================================");

        network.findRoutingPath(0, 5);
        System.out.println("--------------------------------------------------------------------------");
        network.analyzeNetworkConnectivity();
        System.out.println("==========================================================================");
    }
}
