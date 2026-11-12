# Lab 05: Constraint Satisfaction Benchmark System

## 1. Problem Statement / Aim
To architect an experimental benchmark system in Java that quantifies the state-space pruning power of **Backtracking** relative to **Exhaustive Brute-Force Search**, measuring explored states, runtime scalability, and solution counts across $N \in \{4, 6, 8, 10\}$.

---

## 2. Methodology & Pruning Analysis
1. **Exhaustive Search Formulation:** Recursively enumerates every candidate configuration ($N^N$), testing legality only when all $N$ positions are populated.
2. **Backtracking Formulation:** Evaluates safety constraints incrementally, dynamically pruning subtrees the moment any partial assignment violates problem criteria.
3. **Metrics Tracked:** Total visited search-tree nodes, validated solutions, and elapsed execution time in milliseconds.

---

## 3. Experimental Results
```text
=========================================================================================
                   CONSTRAINT SATISFACTION BENCHMARK SYSTEM                              
            (Comparative Search Space Analysis: Backtracking vs. Exhaustive)             
=========================================================================================
EXHAUSTIVE SEARCH VS. BACKTRACKING PRUNING (N = 4):
Strategy           | Explored States  | Solutions Found  | Time (ms)     
-----------------------------------------------------------------------------------------
Exhaustive Brute   | 341              | 2                | 0.0743        
Backtracking Prune | 17               | 2                | 0.0078        
State Space Reduction: 95.01%

-----------------------------------------------------------------------------------------

SCALABILITY ANALYSIS ACROSS PROBLEM SIZES (BACKTRACKING):
N      | Theoretical N^N  | Explored Nodes     | Solutions Found  | Time (ms)     
-----------------------------------------------------------------------------------------
4      | 256              | 17                 | 2                | 0.0087        
6      | 46656            | 153                | 4                | 0.1289        
8      | 16777216         | 2057               | 92               | 0.4806        
10     | 10000000000      | 35539              | 724              | 4.0877        
=========================================================================================
```

---

## 4. Output Screenshot
*(Screenshot will be uploaded upon test execution)*
![Constraint Satisfaction Output](output/output.png)

---

## 5. Learning Outcomes
- Numerically demonstrated that Backtracking achieves over $95\%$ state-space pruning even for small problems ($N=4$), expanding exponentially higher for $N=10$ ($35,539$ nodes examined out of $10^{10}$ theoretical states).
- Verified the superiority of depth-first bounding over naive blind enumeration.
