# Master Complexity Analysis

This reference manual documents the asymptotic computational time and space bounds of all algorithms implemented throughout this laboratory.

## Master Summary Table

| Category | Algorithm | Best Case | Average Case | Worst Case | Space Complexity |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Searching** | Binary Search (DC) | $\mathcal{O}(1)$ | $\mathcal{O}(\log n)$ | $\mathcal{O}(\log n)$ | $\mathcal{O}(\log n)$ |
| **Divide & Conquer** | Max-Min | $\Theta(n)$ | $\Theta(n)$ ($1.5n-2$ comps) | $\Theta(n)$ | $\mathcal{O}(\log n)$ |
| **Divide & Conquer** | Merge Sort | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n)$ |
| **Divide & Conquer** | Quick Sort | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(\log n)$ |
| **Divide & Conquer** | Heap Sort | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(1)$ |
| **Divide & Conquer** | Strassen Matrix Mult | $\Theta(n^{\log_2 7})$ | $\Theta(n^{2.807})$ | $\Theta(n^{2.807})$ | $\mathcal{O}(n^2)$ |
| **Greedy Method** | Fractional Knapsack | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n)$ |
| **Greedy Method** | Job Sequencing | $\mathcal{O}(n \log n)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(n^2)$ | $\mathcal{O}(d_{max})$ |
| **Greedy Method** | Prim's Algorithm (Matrix) | $\mathcal{O}(V^2)$ | $\mathcal{O}(V^2)$ | $\mathcal{O}(V^2)$ | $\mathcal{O}(V^2)$ |
| **Greedy Method** | Kruskal's Algorithm (DSU) | $\mathcal{O}(E \log E)$ | $\mathcal{O}(E \log V)$ | $\mathcal{O}(E \log V)$ | $\mathcal{O}(V + E)$ |
| **Dynamic Prog** | 0/1 Knapsack | $\mathcal{O}(n \cdot W)$ | $\mathcal{O}(n \cdot W)$ | $\mathcal{O}(n \cdot W)$ | $\mathcal{O}(n \cdot W)$ |
| **Dynamic Prog** | Multistage Graph | $\mathcal{O}(V + E)$ | $\mathcal{O}(V + E)$ | $\mathcal{O}(V + E)$ | $\mathcal{O}(V)$ |
| **Dynamic Prog** | Floyd-Warshall | $\Theta(V^3)$ | $\Theta(V^3)$ | $\Theta(V^3)$ | $\mathcal{O}(V^2)$ |
| **Dynamic Prog** | Bellman-Ford | $\mathcal{O}(V \cdot E)$ | $\mathcal{O}(V \cdot E)$ | $\mathcal{O}(V \cdot E)$ | $\mathcal{O}(V)$ |
| **Dynamic Prog** | Traveling Salesperson | $\Theta(n^2 2^n)$ | $\Theta(n^2 2^n)$ | $\Theta(n^2 2^n)$ | $\mathcal{O}(n 2^n)$ |
| **Traversal** | BFS / DFS (List) | $\mathcal{O}(V + E)$ | $\mathcal{O}(V + E)$ | $\mathcal{O}(V + E)$ | $\mathcal{O}(V)$ |
| **Backtracking** | N-Queens | $\mathcal{O}(N)$ | $\mathcal{O}(N!)$ (pruned) | $\mathcal{O}(N!)$ | $\mathcal{O}(N)$ |
| **Backtracking** | Sum of Subsets | $\mathcal{O}(1)$ | $\mathcal{O}(2^n)$ (pruned) | $\mathcal{O}(2^n)$ | $\mathcal{O}(n)$ |
| **Branch & Bound**| 0/1 Knapsack LCBB | $\mathcal{O}(n \log n)$ | Pruned Search | $\mathcal{O}(2^n)$ | $\mathcal{O}(2^n)$ |
| **Branch & Bound**| TSP (Reduced Matrix) | $\mathcal{O}(n^2)$ | Pruned Search | $\mathcal{O}(n!)$ | $\mathcal{O}(n^2 \cdot \text{Nodes})$ |
