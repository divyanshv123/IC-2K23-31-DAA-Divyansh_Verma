# Lab 03: Graph Coloring Problem (m-Colorability)

## 1. Problem Statement / Aim
To formulate and implement the Graph $m$-Coloring problem in Java using Backtracking to assign one of $m$ available colors to every vertex in an undirected graph such that no two adjacent vertices share the identical color.

---

## 2. Algorithm / Approach
Graph coloring is a canonical NP-complete decision problem:

1. **State Vector:** Array `color[V]` where `color[i]` represents color index assigned to vertex $i$ ($0$ means uncolored).
2. **Safety Function (`isSafe`):** Check that no adjacent neighbor of vertex $v$ already holds the candidate color $c$.
3. **Recursive Assignment:**
   - Base Case: If all $V$ vertices have been assigned colors, return `true`.
   - Iterate through colors $1 \dots m$:
     - If candidate color $c$ is safe for current vertex $v$:
       - Assign `color[v] = c`.
       - Recursively solve for $v + 1$.
       - If recursive call succeeds, return `true`.
       - If it leads to a dead end, backtrack by resetting `color[v] = 0`.
   - If no color works, return `false`.

---

## 3. Pseudocode
```text
Algorithm GraphColoring(vertex, graph, m, color, V):
    if vertex == V return true

    for c = 1 to m do:
        if isSafe(vertex, graph, color, c) then:
            color[vertex] = c
            if GraphColoring(vertex + 1, graph, m, color, V) then:
                return true
            color[vertex] = 0

    return false
```

---

## 4. Time & Space Complexity Analysis
- **Time Complexity:**
  - In the worst case: $\mathcal{O}(m^V)$ state-tree branches.
  - Backtracking with safety pruning significantly narrows search depth in practical applications (e.g. register allocation, map coloring).
- **Space Complexity:**
  - `color[]` array and recursion stack: $\mathcal{O}(V)$.

---

## 5. Sample Input
- 4 Vertices with edges:
  - $(0, 1), (0, 2), (0, 3), (1, 2), (2, 3)$
- Available Colors $m = 3$

---

## 6. Sample Output
```text
GRAPH COLORING PROBLEM (M-COLORABILITY)
Number of Vertices: 4
Available Colors (m): 3
----------------------------------------
Valid Color Assignment Found:
Vertex     | Color ID     | Color Name  
----------------------------------------
V0         | 1            | Red         
V1         | 2            | Green       
V2         | 3            | Blue        
V3         | 2            | Green       
----------------------------------------
```

---

## 7. Output Screenshot
![Graph Coloring Output](output/output.png)

---

## 8. Learning Outcomes
- Explored NP-complete constraint satisfaction problems solved via backtracking.
- Understood practical applications in scheduling, timetable generation, and compiler register allocation.
