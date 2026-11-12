# Algorithm Flowcharts

This directory contains algorithmic flowcharts and logic flow diagrams for core algorithms across all units of Design and Analysis of Algorithms (IC-709 B).

```mermaid
flowchart TD
    Start([Start]) --> Input[Input Problem Parameters]
    Input --> SelectParadigm{Select Algorithmic Paradigm}
    
    SelectParadigm -->|Divide & Conquer| DC[Divide into subproblems -> Solve recursively -> Merge]
    SelectParadigm -->|Greedy Method| GM[Sort by greedy metric -> Locally optimal choice -> Build solution]
    SelectParadigm -->|Dynamic Programming| DP[Identify overlapping subproblems -> Formulate recurrence -> Tabulate / Memoize]
    SelectParadigm -->|Backtracking| BT[Build candidate incrementally -> Check constraints -> Backtrack if dead end]
    SelectParadigm -->|Branch & Bound| BB[State-space queue -> Calculate lower/upper bounds -> Prune suboptimal nodes]
    
    DC --> Verify[Verify Optimality & Complexity]
    GM --> Verify
    DP --> Verify
    BT --> Verify
    BB --> Verify
    Verify --> End([End / Return Result])
```

## Flowchart Index
1. **Divide and Conquer:** Merge Sort, Quick Sort, Binary Search
2. **Greedy Method:** Fractional Knapsack, Prim's & Kruskal's MST
3. **Dynamic Programming:** 0/1 Knapsack, Floyd-Warshall, TSP
4. **Backtracking:** N-Queens, Sum of Subsets
5. **Branch and Bound:** 0/1 Knapsack LCBB, TSP Reduced Cost Matrix
