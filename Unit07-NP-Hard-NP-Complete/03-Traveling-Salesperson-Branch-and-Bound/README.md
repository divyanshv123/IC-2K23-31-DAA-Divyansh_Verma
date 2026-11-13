# Lab 03: Traveling Salesperson Problem using Branch and Bound

## 1. Problem Statement / Aim
To formulate, implement, and analyze the Traveling Salesperson Problem (TSP) in Java using the Least-Cost Branch and Bound (LCBB) method with reduced cost matrices to compute lower bounds and prune non-promising partial Hamiltonian paths.

---

## 2. Algorithm / Approach
1. **Reduced Cost Matrix:**
   - A matrix where every row and column has at least one $0$ entry.
   - Reduction is achieved by subtracting the minimum non-infinity value from each row and column.
   - The sum of subtracted constants represents a strict **lower bound** on the tour cost for that state.
2. **State Space Generation:**
   - Root represents starting city 0.
   - Transitioning from city $i$ to city $j$:
     - Set row $i$ and column $j$ to $\infty$ (cannot leave $i$ again or enter $j$ again).
     - Set entry $(j, 0) = \infty$ to prevent premature tour closure.
     - Child lower bound:
       $$\text{Cost}_{child} = \text{Cost}_{parent} + A[i][j] + \text{ReductionCost}(A_{child})$$
3. **Pruning Strategy:**
   - Expand the live node with minimal cost using a Min-Priority Queue.
   - If a node's lower bound $\ge \text{currentBestTourCost}$, prune the node.

---

## 3. Pseudocode
```text
Algorithm TSPBranchAndBound(costMatrix, n):
    pq = new MinPriorityQueue(ordered by node.cost)
    root = createRootNode(costMatrix)
    root.cost = reduceMatrix(root.matrix)
    pq.insert(root)
    minCost = infinity

    while not pq.isEmpty() do:
        curr = pq.extractMin()
        if curr.cost >= minCost then continue
        if curr.level == n - 1 then:
            updateBestTour(curr)
            continue

        for each unvisited city j do:
            child = branchToCity(curr, j)
            if child.cost < minCost then:
                pq.insert(child)

    return minCost, bestTour
```

---

## 4. Time & Space Complexity Analysis
- **Time Complexity:**
  - Worst Case: $\mathcal{O}(n!)$ or $\mathcal{O}(n^2 2^n)$ in adversarial symmetric graphs.
  - Average Case: Significant pruning reduces the search tree compared to brute-force permutations.
- **Space Complexity:**
  - Storing reduced matrices ($n \times n$) in priority queue nodes: $\mathcal{O}(n^2 \cdot \text{active live nodes})$.

---

## 5. Sample Input & Output
```text
Enter total number of cities: 4
Enter the travel cost matrix:
0 10 15 20
10 0 35 25
15 35 0 30
20 25 30 0

Travelling Salesman Result
------------------------------
Best Route: City 1 -> City 2 -> City 4 -> City 3 -> City 1
Minimum Travelling Cost: 80
```

---

## 6. Output Screenshot
![TSP B&B Output](output/output.png)

---

## 7. Learning Outcomes
- Understood the concept of lower bounding through linear algebraic matrix reduction.
- Compared best-first LC branch and bound search against depth-first backtracking.
