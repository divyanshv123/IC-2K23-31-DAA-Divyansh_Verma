# Lab 03: Prim's Minimum Spanning Tree Algorithm

## 1. Problem Statement / Aim
To design, implement, and analyze Prim's Algorithm in Java to find the Minimum Cost Spanning Tree (MST) of a connected, weighted, undirected graph using a greedy strategy.

---

## 2. Algorithm / Approach
Prim's algorithm builds an MST vertex-by-vertex starting from an arbitrary root vertex:

1. **Initialization:**
   - Maintain an array `key[]` storing minimum weight edge connecting each vertex to current MST (initialized to $\infty$, $key[0] = 0$).
   - Maintain a boolean array `includedInMST[]` to track vertices added to the MST.
   - Maintain a `parent[]` array to store edges forming the tree.
2. **Greedy Expansion:**
   - Repeat $V - 1$ times:
     - Select vertex $u$ with minimum `key` value that is not yet included in MST.
     - Include $u$ in MST (`includedInMST[u] = true`).
     - Update key values and parent pointers for all adjacent vertices $v$ of $u$ if edge weight $(u, v) < key[v]$ and $v$ is not yet included in MST.
3. **Output Construction:**
   - Iterate through `parent[]` array to output all $V-1$ edges and compute total MST cost.

---

## 3. Pseudocode
```text
Algorithm PrimMST(graph, V):
    parent = array of size V
    key = array of size V filled with infinity
    included = boolean array of size V filled with false

    key[0] = 0
    parent[0] = -1

    for count = 0 to V - 2 do:
        u = vertex with minimum key value among vertices not in included
        included[u] = true

        for v = 0 to V - 1 do:
            if graph[u][v] != 0 and included[v] is false and graph[u][v] < key[v] then:
                parent[v] = u
                key[v] = graph[u][v]

    return parent
```

---

## 4. Time & Space Complexity Analysis
- **Time Complexity:**
  - With Adjacency Matrix (linear scan for min vertex):
    - Finding minimum key vertex: $\mathcal{O}(V)$ per iteration $\times V$ iterations = $\mathcal{O}(V^2)$.
    - Updating adjacency weights: $\mathcal{O}(V^2)$.
    - **Overall Time Complexity:** $\mathcal{O}(V^2)$ (Can be reduced to $\mathcal{O}(E \log V)$ using Min-Heap / PriorityQueue with adjacency list).
- **Space Complexity:**
  - `key`, `parent`, and `includedInMST` arrays: $\mathcal{O}(V)$.
  - Adjacency matrix storage: $\mathcal{O}(V^2)$.
  - **Auxiliary Space Complexity:** $\mathcal{O}(V)$.

---

## 5. Sample Input
Graph with $V = 5$ vertices represented as adjacency matrix:
```text
  0  2  0  6  0
  2  0  3  8  5
  0  3  0  0  7
  6  8  0  0  9
  0  5  7  9  0
```

---

## 6. Sample Output
```text
PRIM'S MINIMUM SPANNING TREE ALGORITHM
Number of Vertices: 5
--------------------------------------
Edges in the Minimum Spanning Tree (MST):
Edge       | Weight  
---------------------
0 - 1       | 2       
1 - 2       | 3       
0 - 3       | 6       
1 - 4       | 5       
---------------------
Total Minimum Cost: 16
```

---

## 7. Output Screenshot
![Prim's Algorithm Output](output/output.png)

---

## 8. Learning Outcomes
- Understood how Prim's algorithm maintains a single growing tree of vertices and expands it using cut-edge properties.
- Analyzed when Prim's is preferred over Kruskal's (e.g., dense graphs where $E \approx V^2$).
