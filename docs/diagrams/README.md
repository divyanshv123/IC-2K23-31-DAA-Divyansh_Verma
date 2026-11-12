# Algorithmic Architecture Diagrams

Visual representation of data structures, state space trees, and memory models.

## 1. Divide and Conquer Tree
```mermaid
graph TD
    P["Problem of Size N"] --> SP1["Subproblem N/2"]
    P --> SP2["Subproblem N/2"]
    SP1 --> L1["Subproblem N/4"]
    SP1 --> L2["Subproblem N/4"]
    SP2 --> L3["Subproblem N/4"]
    SP2 --> L4["Subproblem N/4"]
    L1 --> Base1["Base Case"]
    L2 --> Base2["Base Case"]
    L3 --> Base3["Base Case"]
    L4 --> Base4["Base Case"]
```

## 2. Dynamic Programming vs. Greedy Choice
```mermaid
graph LR
    subgraph Greedy
        G1[Local Decision] --> G2[Next Decision]
        G2 --> G3[Next Decision]
    end
    subgraph DP
        D1[Subproblem 1] --> M[(Memo / Table)]
        D2[Subproblem 2] --> M
        M --> Opt[Global Optimum]
    end
```

## 3. Branch and Bound State Space Tree
```mermaid
graph TD
    Root["Root Node (Bound = U0)"] --> Child1["Include Item 1 (Bound = U1)"]
    Root --> Child2["Exclude Item 1 (Bound = U2)"]
    Child1 --> C3["Include Item 2 (Feasible)"]
    Child1 --> C4["Exclude Item 2 (Pruned: Bound < Best)"]
    Child2 --> C5["Include Item 2 (Bound = U5)"]
```
