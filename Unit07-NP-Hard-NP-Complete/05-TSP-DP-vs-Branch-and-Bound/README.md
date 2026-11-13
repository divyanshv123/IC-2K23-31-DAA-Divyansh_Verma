# Lab 05: Traveling Salesperson Problem: Dynamic Programming vs. Branch and Bound

## 1. Problem Statement / Aim
To empirically evaluate, benchmark, and compare two exact optimization algorithms for the Traveling Salesperson Problem (TSP)—**Dynamic Programming (Held-Karp)** and **Branch and Bound (Reduced Cost Matrix)**—measuring execution times, solution consistency, and computational scaling across $N \in \{4, 6, 8, 10\}$ cities.

---

## 2. Theoretical Comparison

| Metric / Feature | Held-Karp Dynamic Programming | Branch and Bound (Reduced Matrix) |
| :--- | :--- | :--- |
| **Asymptotic Complexity** | Deterministic: $\Theta(n^2 \cdot 2^n)$ | Worst-case: $\mathcal{O}(n!)$; average-case pruned |
| **State Representation** | Bitmask integer representing subset of visited cities | Reduced cost matrix of dimension $n \times n$ |
| **Search Order** | Systematic subset sizes ($1, 2, \dots, n$) | Best-First (LC) exploration guided by lower bound |
| **Memory Footprint** | Static table of size $(2^n \times n)$ integers | Dynamic heap storing live matrix instances |

---

## 3. Sample Benchmark Output
```text
=========================================================================================
            TRAVELING SALESPERSON: DYNAMIC PROGRAMMING VS. BRANCH AND BOUND              
=========================================================================================
Cities (N) | DP Cost      | B&B Cost     | DP Time (ms)   | B&B Time (ms)  | Status  
-----------------------------------------------------------------------------------------
4          | 65           | 65           | 0.0562         | 2.5876         | PASSED  
6          | 246          | 246          | 0.0262         | 0.1721         | PASSED  
8          | 195          | 195          | 0.1553         | 0.2316         | PASSED  
10         | 235          | 235          | 0.7383         | 0.6408         | PASSED  
=========================================================================================
```

---

## 4. Output Screenshot
![TSP Comparison Output](output/output.png)

---

## 5. Learning Outcomes
- Confirmed that both algorithmic paradigms yield identically optimal tour lengths.
- Evaluated performance inflection points: For moderate $N$, Branch and Bound aggressively eliminates large swaths of the factorial search space, matching or outperforming DP without needing pre-allocated bitmask state tables.
