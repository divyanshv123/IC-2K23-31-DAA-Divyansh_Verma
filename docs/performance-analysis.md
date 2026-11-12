# Comprehensive Performance Analysis Report

## Design & Analysis of Algorithms Laboratory (IC-709 B)
**Student Name:** Divyansh Verma  
**Roll No:** IC-2K23-31  
**Affiliation:** International Institute of Professional Studies (IIPS), Devi Ahilya Vishwavidyalaya (DAVV), Indore  

---

## 1. Executive Summary
This document consolidates the empirical runtime and scalability benchmarks performed across all 7 units of the Design and Analysis of Algorithms curriculum.

### Major Empirical Findings:
1. **Sorting Performance (Unit 02):**
   - Quick Sort demonstrated the fastest CPU cycle runtime ($5.295\text{ ms}$ for $80,000$ elements) due to superior cache locality.
   - Merge Sort maintained strictly stable performance ($8.660\text{ ms}$) with higher memory allocation overhead.
   - Heap Sort proved strictly consistent in-place execution ($7.461\text{ ms}$) without auxiliary memory allocation.
2. **MST Construction (Unit 03):**
   - On dense graphs ($E \propto V^2$), Prim's algorithm ($\mathcal{O}(V^2)$) achieved lower runtime overhead compared to Kruskal's edge-sorting phase ($\mathcal{O}(E \log E)$).
3. **Graph Exploration (Unit 05):**
   - Adjacency lists achieved orders-of-magnitude faster traversals on sparse graphs compared to adjacency matrices.
4. **Combinatorial Pruning (Unit 06 & Unit 07):**
   - In N-Queens ($N=10$), Backtracking explored only $35,539$ nodes out of $10^{10}$ theoretical configurations ($>99.999\%$ pruning).
   - In 0/1 Knapsack ($N=20$), Branch and Bound pruned the search space to just 43 nodes, bypassing $2^{20} = 1,048,576$ exhaustive evaluations.
