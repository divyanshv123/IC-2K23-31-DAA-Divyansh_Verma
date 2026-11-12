# Lab 01: Exhaustive (Brute-Force) Search Demonstration

## 1. Problem Statement / Aim
To formulate and implement an Exhaustive (Brute-Force) Search algorithm in Java for a combinatorial optimization problem (0/1 Knapsack) that systematically evaluates all $2^n$ candidate subset states, demonstrating why exhaustive search becomes computationally intractable as input size scales.

---

## 2. Algorithm / Approach
Exhaustive search generates every candidate solution in the search space without applying intelligent bounding or pruning:

1. **State Space Enumeration:** For an input of $n$ items, there are $2^n$ distinct subsets.
2. **Binary Bitmasking:** Use bitmask $mask \in [0, 2^n - 1]$ where bit $i$ indicates whether item $i$ is included ($1$) or excluded ($0$).
3. **Evaluation:** For each candidate subset, compute total weight and total value.
4. **Feasibility Check:** If total weight $\le capacity$, check if total value exceeds the current best.

---

## 3. Sample Input & Output
```text
EXHAUSTIVE (BRUTE-FORCE) SEARCH DEMONSTRATION
Problem: 0/1 Knapsack (Total Items: 4, Capacity: 15)
----------------------------------------------------------------------
Subset Mask  | Binary Selection | Total Weight | Total Value  | Feasible
----------------------------------------------------------------------
State 0      | 0000             | 0            | 0            | YES     
State 1      | 1000             | 2            | 20           | YES     
State 2      | 0100             | 5            | 30           | YES     
State 3      | 1100             | 7            | 50           | YES     
State 4      | 0010             | 10           | 50           | YES     
State 5      | 1010             | 12           | 70           | YES     
State 6      | 0110             | 15           | 80           | YES     
State 7      | 1110             | 17           | 100          | NO (Exceeds)
State 8      | 0001             | 5            | 70           | YES     
State 9      | 1001             | 7            | 90           | YES     
State 10     | 0101             | 10           | 100          | YES     
State 11     | 1101             | 12           | 120          | YES     
State 12     | 0011             | 15           | 120          | YES     
State 13     | 1011             | 17           | 140          | NO (Exceeds)
State 14     | 0111             | 20           | 150          | NO (Exceeds)
State 15     | 1111             | 22           | 170          | NO (Exceeds)
----------------------------------------------------------------------
Total Candidate States Evaluated: 16
Optimal Feasible Value:           120
Selected Item Indices (0-based):  0 1 3 
```

---

## 4. Complexity Analysis
- **Time Complexity:** $\mathcal{O}(n \cdot 2^n)$ — evaluates all $2^n$ configurations, spending $\mathcal{O}(n)$ checking each configuration.
- **Space Complexity:** $\mathcal{O}(1)$ auxiliary space beyond input arrays.

---

## 5. Output Screenshot
*(Screenshot will be uploaded upon test execution)*
![Exhaustive Search Output](output/output.png)

---

## 6. Learning Outcomes
- Demonstrated exhaustive generation on small $N$ to establish ground-truth benchmarks for comparing Branch & Bound and Dynamic Programming.
- Observed the exponential growth of state spaces in combinatorial problems.
