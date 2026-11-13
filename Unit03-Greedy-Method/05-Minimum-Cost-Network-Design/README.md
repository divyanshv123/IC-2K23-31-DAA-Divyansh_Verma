# Lab 05: Minimum-Cost Network Design System

## 1. Problem Statement / Aim
To design, implement, and benchmark a Minimum-Cost Network Design System in Java that empirically evaluates and compares **Prim's** and **Kruskal's** algorithms on synthetic connected graphs of varying vertex counts ($V \in \{20, 50, 100, 200\}$) and densities, verifying solution optimality and analyzing execution characteristics.

---

## 2. Algorithm / Approach
1. **Random Graph Generation:** Generate connected undirected graphs with $V$ vertices and edge density $d = 0.3$. First, create a spanning path ($i \leftrightarrow i+1$) to ensure connectivity, then add cross edges with probability $d$.
2. **Dual Representation:** Represent the identical network simultaneously as:
   - Adjacency Matrix (for Prim's algorithm)
   - Edge List (for Kruskal's algorithm)
3. **Execution & Timing:**
   - Execute Prim's algorithm using high-precision nano-timing (`System.nanoTime()`).
   - Execute Kruskal's algorithm using disjoint-set union on the same graph with nano-timing.
4. **Validation:** Automatically assert that $Cost_{Prim} == Cost_{Kruskal}$ across all graph sizes.
5. **Reporting:** Tabulate nodes, edges, generated MST costs, execution runtimes, and validation status.

---

## 3. Comparative Complexity

| Feature | Prim's Algorithm | Kruskal's Algorithm |
| :--- | :--- | :--- |
| **Data Structure** | Adjacency Matrix / Priority Queue | Edge List + Disjoint Set (DSU) |
| **Time Complexity** | $\mathcal{O}(V^2)$ (Matrix) / $\mathcal{O}(E \log V)$ (Heap) | $\mathcal{O}(E \log E)$ or $\mathcal{O}(E \log V)$ |
| **Space Complexity** | $\mathcal{O}(V^2)$ | $\mathcal{O}(V + E)$ |
| **Ideal Graph Type** | Dense graphs ($E \approx V^2$) | Sparse graphs ($E \ll V^2$) |

---

## 4. Java Implementation
Refer to [`NetworkDesignComparison.java`](NetworkDesignComparison.java) for the complete implementation.

---

## 5. Sample Input / Experimental Setup
- Vertex sizes: 20, 50, 100, 200
- Edge density: 0.30
- Fixed seed: 12345
- Edge weight range: [1, 100]

---

## 6. Sample Output
```text
=========================================================================================
                     MINIMUM-COST NETWORK DESIGN SYSTEM                                  
                (Comparative Evaluation: Prim's vs Kruskal's MST)                        
=========================================================================================
Nodes (V)  | Edges (E)  | Prim's Cost  | Kruskal's Cost | Prim's Time(ms) | Krusk Time(ms) | Match   
-----------------------------------------------------------------------------------------
20         | 73         | 332          | 332            | 0.016          | 1.258          | PASSED  
50         | 411        | 356          | 356            | 0.076          | 0.219          | PASSED  
100        | 1539       | 444          | 444            | 0.292          | 0.987          | PASSED  
200        | 6051       | 443          | 443            | 0.945          | 4.766          | PASSED  
=========================================================================================
```

---

## 7. Output Screenshot
![Network Design Comparison Output](output/output.png)

---

## 8. Learning Outcomes
- Verified experimentally that both greedy MST algorithms converge to identical minimum spanning tree total weights.
- Observed that on dense graphs ($E \propto V^2$), Prim's adjacency matrix implementation performs very efficiently, while Kruskal's runtime scales with the edge sorting cost $\mathcal{O}(E \log E)$.
- Built an automated benchmarking harness with automated correctness verification.
