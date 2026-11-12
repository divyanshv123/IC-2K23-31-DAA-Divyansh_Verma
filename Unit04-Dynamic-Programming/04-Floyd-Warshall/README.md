# Lab 04: All-Pairs Shortest Paths (Floyd-Warshall)

## 1. Problem Statement / Aim
To implement and analyze the Floyd-Warshall algorithm in Java using Dynamic Programming to find the shortest distances between all pairs of vertices in a directed, edge-weighted graph (supporting negative weights without negative-weight cycles).

---

## 2. Algorithm / Approach
The Floyd-Warshall algorithm evaluates whether using an intermediate vertex $k$ yields a shorter path between vertex $i$ and vertex $j$:

1. **Matrix Initialization:** Initialize distance matrix $D^{(0)}$ with edge weights $W(i, j)$, setting $D[i][i] = 0$ and $D[i][j] = \infty$ if no direct edge exists.
2. **Intermediate Vertex Relaxation:**
   - Iterate intermediate vertex $k$ from $0$ to $V - 1$.
   - For every source vertex $i$ and destination vertex $j$:
     $$D^{(k)}[i][j] = \min \left( D^{(k-1)}[i][j], \; D^{(k-1)}[i][k] + D^{(k-1)}[k][j] \right)$$
3. **Result:** The final matrix $D^{(V)}$ contains the all-pairs shortest paths.

---

## 3. Pseudocode
```text
Algorithm FloydWarshall(graph, V):
    dist = copy of graph

    for k = 0 to V - 1 do:
        for i = 0 to V - 1 do:
            for j = 0 to V - 1 do:
                if dist[i][k] + dist[k][j] < dist[i][j] then:
                    dist[i][j] = dist[i][k] + dist[k][j]

    return dist
```

---

## 4. Time & Space Complexity Analysis
- **Time Complexity:**
  - Three nested loops each running $V$ times: $\mathcal{O}(V^3)$.
- **Space Complexity:**
  - $V \times V$ distance matrix: $\mathcal{O}(V^2)$.
  - In-place matrix update allows $\mathcal{O}(V^2)$ total auxiliary space.

---

## 5. Sample Input
Graph with $V = 4$ vertices and weight matrix ($INF = 99999$):
```text
   0    3  INF    7
   8    0    2  INF
   5  INF    0    1
   2  INF  INF    0
```

---

## 6. Sample Output
```text
ALL-PAIRS SHORTEST PATHS (FLOYD-WARSHALL ALGORITHM)
--------------------------------------------------
Shortest Distance Matrix between every pair of vertices:
          V0    V1    V2    V3
--------------------------------------------------
  V0:      0     3     5     6
  V1:      5     0     2     3
  V2:      3     6     0     1
  V3:      2     5     7     0
```

---

## 7. Output Screenshot
*(Screenshot will be uploaded upon test execution)*
![Floyd-Warshall Output](output/output.png)

---

## 8. Learning Outcomes
- Understood how incrementally allowing larger subsets of intermediate vertices $\{0, 1, \dots, k\}$ constructs optimal substructures for all vertex pairs simultaneously.
- Compared Floyd-Warshall $\mathcal{O}(V^3)$ with running Dijkstra $V$ times ($\mathcal{O}(V(E \log V))$).
