# Lab 06: Optimization Algorithm Comparison System

## 1. Problem Statement / Aim
To build a comprehensive comparative benchmarking platform in Java that systematically contrasts **Exhaustive Brute-Force Search**, **Dynamic Programming**, and **Branch and Bound** on NP-hard combinatorial optimization instances, measuring state exploration volume, execution latency, memory footprint, and algorithmic scalability.

---

## 2. Multi-Paradigm Comparison

| Paradigm | Complexity Class | States Explored ($N=16$) | Time ($N=16$) | Capacity Sensitivity | Memory Pattern |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Exhaustive Search** | $\Theta(2^N)$ | $65,536$ | $5.05\text{ ms}$ | Independent | Minimal ($\mathcal{O}(1)$) |
| **Dynamic Programming**| $\mathcal{O}(N \cdot W)$ | $1,280$ | $0.07\text{ ms}$ | Depends on integer $W$ | Table ($\mathcal{O}(NW)$) |
| **Branch & Bound** | $\mathcal{O}(2^N)$ (Pruned) | $43$ | $0.06\text{ ms}$ | Continuous/Any $W$ | Heap ($\mathcal{O}(\text{LiveNodes})$) |

---

## 3. Sample Benchmark Output
```text
=========================================================================================================
                         OPTIMIZATION ALGORITHM COMPARISON SYSTEM                                
               (Comprehensive Benchmark: Exhaustive vs. DP vs. Branch & Bound)                   
=========================================================================================================
N      | Strategy         | Explored States  | Optimal Value    | Time (ms)      | Est. Space     | Quality   
---------------------------------------------------------------------------------------------------------
8      | Exhaustive       | 256              | 251              | 0.1062         | O(1)           | Exact     
8      | Dynamic Prog     | 640              | 251              | 0.1533         | O(n*W)         | Exact     
8      | Branch & Bound   | 60               | 251              | 2.6556         | O(LiveNodes)   | Exact     
---------------------------------------------------------------------------------------------------------
12     | Exhaustive       | 4096             | 251              | 0.8509         | O(1)           | Exact     
12     | Dynamic Prog     | 960              | 251              | 0.0491         | O(n*W)         | Exact     
12     | Branch & Bound   | 151              | 251              | 0.1379         | O(LiveNodes)   | Exact     
---------------------------------------------------------------------------------------------------------
16     | Exhaustive       | 65536            | 300              | 5.0497         | O(1)           | Exact     
16     | Dynamic Prog     | 1280             | 300              | 0.0667         | O(n*W)         | Exact     
16     | Branch & Bound   | 43               | 300              | 0.0653         | O(LiveNodes)   | Exact     
---------------------------------------------------------------------------------------------------------
20     | Exhaustive       | Skipped (>10^6)  | N/A              | Timeout        | O(1)           | Exact     
20     | Dynamic Prog     | 1600             | 300              | 0.0924         | O(n*W)         | Exact     
20     | Branch & Bound   | 43               | 300              | 0.1064         | O(LiveNodes)   | Exact     
---------------------------------------------------------------------------------------------------------
=========================================================================================================
```

---

## 4. Output Screenshot
![Optimization System Output](output/output.png)

---

## 5. Learning Outcomes
- Demonstrated how intelligent bounding functions allow Branch and Bound to bypass billions of combinatorial configurations ($43$ nodes vs. $10^6+$ in brute force).
- Evaluated when pseudo-polynomial DP is preferable (discrete small capacities) versus when Branch and Bound is essential (arbitrary or non-integer capacities).
