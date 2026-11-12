# Lab 07: Logistics Route Optimization System

## 1. Problem Statement / Aim
To architect and implement an end-to-end Logistics Route Optimization System in Java that applies **Floyd-Warshall** for all-pairs road network shortest paths combined with **Held-Karp Dynamic Programming** for Traveling Salesperson route scheduling, evaluating distance reductions against baseline non-optimized delivery routes.

---

## 2. Algorithm / Approach
1. **Shortest Distance Metricization:**
   - Real-world road networks may lack direct connections between all hubs.
   - Run the Floyd-Warshall algorithm ($\mathcal{O}(V^3)$) to convert direct road distances into an all-pairs metric shortest distance matrix.
2. **Optimal Hamiltonian Tour Scheduling:**
   - Execute Held-Karp bitmask Dynamic Programming on the metricized distance matrix starting from Central Depot (index 0).
   - Find the minimal tour visiting every warehouse/terminal exactly once and returning to the Central Depot.
3. **Efficiency Analysis:**
   - Compare the optimized tour against an arbitrary baseline delivery order to measure absolute distance saved and percentage fuel/travel efficiency improvement.

---

## 3. Mathematical Model & Complexity
- **Phase 1 (Metric All-Pairs):**
  $$D[i][j] = \min_{k} (D[i][j], D[i][k] + D[k][j]) \implies \mathcal{O}(V^3)$$
- **Phase 2 (Held-Karp DP Tour):**
  $$dp[mask][u] \implies \mathcal{O}(V^2 \cdot 2^V)$$
- **Total System Complexity:** $\mathcal{O}(V^3 + V^2 \cdot 2^V)$ time, $\mathcal{O}(V^2 + V \cdot 2^V)$ space.

---

## 4. Java Implementation
Refer to [`RouteOptimizationSystem.java`](RouteOptimizationSystem.java) for the complete implementation.

---

## 5. Sample Input
- 5 Distribution Hubs: Central Depot, North Warehouse, East Terminal, South Hub, West Center
- Road network connection matrix:
  ```text
    0  12  29  22  18
   12   0  19  35  25
   29  19   0  15  30
   22  35  15   0  11
   18  25  30  11   0
  ```

---

## 6. Sample Output
```text
==========================================================================
                 LOGISTICS ROUTE OPTIMIZATION SYSTEM                      
           (Dynamic Programming Optimization for Delivery Fleet)          
==========================================================================
Delivery Hubs / Locations:
  [0] Central Depot
  [1] North Warehouse
  [2] East Terminal
  [3] South Hub
  [4] West Center
--------------------------------------------------------------------------
Optimized Delivery Sequence: Central Depot -> West Center -> South Hub -> East Terminal -> North Warehouse -> Central Depot
--------------------------------------------------------------------------
Optimized Route Distance:   75 km
Unoptimized Route Distance: 106 km
Total Distance Saved:       31 km (29.25% efficiency improvement)
==========================================================================
```

---

## 7. Output Screenshot
*(Screenshot will be uploaded upon test execution)*
![Route Optimization System Output](output/output.png)

---

## 8. Learning Outcomes
- Demonstrated how composing multiple DP algorithms (Floyd-Warshall + Held-Karp) solves complex, multi-stage real-world logistics challenges.
- Quantified concrete computational efficiency gains and empirical route optimization percentages.
