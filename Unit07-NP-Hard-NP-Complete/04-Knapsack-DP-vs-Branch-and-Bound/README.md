# Lab 04: 0/1 Knapsack: Dynamic Programming vs. Branch and Bound

## 1. Problem Statement / Aim
To empirically benchmark and analyze the 0/1 Knapsack problem comparing **Dynamic Programming (Bottom-Up Tabulation)** against **Least-Cost Branch and Bound**, evaluating runtime, memory allocation patterns, and solution fidelity across increasing dataset sizes ($N \in \{10, 15, 20, 24\}$).

---

## 2. Theoretical Comparison

| Dimension | Dynamic Programming | Branch and Bound |
| :--- | :--- | :--- |
| **Complexity Class** | Pseudo-polynomial: $\mathcal{O}(n \cdot W)$ | Exponential worst-case: $\mathcal{O}(2^n)$; sub-exponential average |
| **Constraint on Weights** | Requires small discrete integer capacities | Operates naturally on fractional, arbitrary, and large weights |
| **Memory Footprint** | Static $(n+1) \times (W+1)$ matrix or $1\text{D}$ array | Dynamic Priority Queue nodes |
| **Pruning Mechanism** | Overlapping subproblem memoization | Upper-bound relaxation pruning |

---

## 3. Sample Benchmark Output
```text
=========================================================================================
                0/1 KNAPSACK: DYNAMIC PROGRAMMING VS. BRANCH AND BOUND                    
=========================================================================================
Items(N) | Capacity   | DP Val       | B&B Val      | DP Time(ms)  | B&B Time(ms) | Status  
-----------------------------------------------------------------------------------------
10       | 100        | 348          | 348          | 0.0838       | 1.7624       | PASSED  
15       | 100        | 440          | 440          | 0.0687       | 0.0318       | PASSED  
20       | 100        | 509          | 509          | 0.1453       | 0.1866       | PASSED  
24       | 100        | 591          | 591          | 0.1539       | 0.0901       | PASSED  
=========================================================================================
```

---

## 4. Output Screenshot
*(Screenshot will be uploaded upon test execution)*
![Knapsack Comparison Output](output/output.png)

---

## 5. Learning Outcomes
- Verified that both algorithms arrive at identical optimal objective values.
- Analyzed the trade-off: DP is deterministic ($\mathcal{O}(nW)$) but memory-bound for huge $W$, whereas B&B is independent of $W$ but sensitivity depends on relaxation bound tightness.
