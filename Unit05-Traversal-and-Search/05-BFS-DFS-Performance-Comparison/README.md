# Lab 05: BFS vs. DFS Performance Comparison

## 1. Problem Statement / Aim
To empirically evaluate, benchmark, and contrast the runtime performance of **Breadth First Search (BFS)** and **Depth First Search (DFS)** implemented across both **Adjacency List** and **Adjacency Matrix** structures on randomly generated connected graphs of increasing sizes ($V \in \{50, 100, 200, 400\}$).

---

## 2. Comparative Analysis

| Feature | Breadth First Search (BFS) | Depth First Search (DFS) |
| :--- | :--- | :--- |
| **Underlying Data Structure** | FIFO Queue | LIFO Stack (or Call Stack) |
| **Exploration Strategy** | Level-by-level (radiates outward) | Branch-by-branch (dives deep) |
| **Shortest Path Property** | Guaranteed minimal hops for unweighted graphs | Not guaranteed to find shortest path |
| **Memory Consumption** | Proportional to maximum breadth ($O(w)$) | Proportional to maximum depth ($O(h)$) |
| **Cycle Detection** | Using cross-edges / visited set | Using back-edges |
| **Time Complexity (List)** | $\mathcal{O}(V + E)$ | $\mathcal{O}(V + E)$ |
| **Time Complexity (Matrix)**| $\mathcal{O}(V^2)$ | $\mathcal{O}(V^2)$ |

---

## 3. Experimental Setup
- Graph Sizes: 50, 100, 200, 400 vertices
- Edge Density: 15% random connectivity
- Benchmark repetitions measured via `System.nanoTime()`.

---

## 4. Sample Output
```text
=========================================================================================
                    BFS VS. DFS PERFORMANCE BENCHMARK                                    
          (Comparison of Traversal Paradigms and Graph Representations)                  
=========================================================================================
Vertices   | BFS List (ms)    | DFS List (ms)    | BFS Matrix (ms)  | DFS Matrix (ms) 
-----------------------------------------------------------------------------------------
50         | 0.5497           | 0.0600           | 0.0472           | 0.0337          
100        | 0.0806           | 0.0545           | 0.1501           | 0.1675          
200        | 0.7184           | 0.5637           | 0.7828           | 0.3157          
400        | 1.7951           | 0.2214           | 1.6782           | 1.3689          
=========================================================================================
```

---

## 5. Output Screenshot
![BFS vs DFS Output](output/output.png)

---

## 6. Learning Outcomes
- Observed that on sparse to moderately dense graphs, Adjacency Lists offer significant speedups over Adjacency Matrices by avoiding wasteful $\mathcal{O}(V)$ row scans.
- Analyzed practical memory footprints: BFS queue sizes can swell up to $\mathcal{O}(V)$ on wide graphs, whereas DFS stack depth depends only on the maximum path length.
