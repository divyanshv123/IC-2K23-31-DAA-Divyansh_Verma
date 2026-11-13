# Lab 04: Sudoku Solver using Backtracking

## 1. Problem Statement / Aim
To formulate and implement a 9x9 Sudoku puzzle solver in Java using Backtracking and Constraint Satisfaction, placing numbers 1 through 9 into empty cells without violating row, column, or $3 \times 3$ box uniqueness rules.

---

## 2. Algorithm / Approach
1. **Find Unassigned Cell:** Scan the $9 \times 9$ grid for the first empty cell (represented by $0$). If no empty cell remains, the puzzle is solved.
2. **Constraint Check (`isSafe`):**
   - Check if digit $num \in \{1 \dots 9\}$ already appears in the same row.
   - Check if $num$ already appears in the same column.
   - Check if $num$ already appears in the corresponding $3 \times 3$ subgrid.
3. **Recursive Placement & Backtrack:**
   - If $num$ is valid: place $num$ in cell $(row, col)$.
   - Recurse to solve the remainder of the puzzle.
   - If recursion succeeds, return `true`.
   - If it leads to a dead end, backtrack by clearing the cell ($board[row][col] = 0$) and trying the next number.

---

## 3. Pseudocode
```text
Algorithm SolveSudoku(grid):
    find an empty cell (row, col)
    if no empty cell exists return true

    for num = 1 to 9 do:
        if isSafe(grid, row, col, num) then:
            grid[row][col] = num
            if SolveSudoku(grid) return true
            grid[row][col] = 0

    return false
```

---

## 4. Time & Space Complexity Analysis
- **Time Complexity:**
  - Worst case: $\mathcal{O}(9^{m})$ where $m$ is the count of empty cells.
  - Subgrid and line constraints prune $>99.9\%$ of paths in practice, solving typical boards in milliseconds.
- **Space Complexity:**
  - Recursion depth at most 81 stack frames: $\mathcal{O}(1)$ auxiliary space.

---

## 5. Sample Input & Output
```text
==================================================
        SUDOKU PUZZLE SOLVER (BACKTRACKING)       
==================================================
Initial Puzzle Grid:
5 3 . | . 7 . | . . . 
6 . . | 1 9 5 | . . . 
. 9 8 | . . . | . 6 . 
------+-------+------
8 . . | . 6 . | . . 3 
4 . . | 8 . 3 | . . 1 
7 . . | . 2 . | . . 6 
------+-------+------
. 6 . | . . . | 2 8 . 
. . . | 4 1 9 | . . 5 
. . . | . 8 . | . 7 9 
--------------------------------------------------
Solved Sudoku Grid:
5 3 4 | 6 7 8 | 9 1 2 
6 7 2 | 1 9 5 | 3 4 8 
1 9 8 | 3 4 2 | 5 6 7 
------+-------+------
8 5 9 | 7 6 1 | 4 2 3 
4 2 6 | 8 5 3 | 7 9 1 
7 1 3 | 9 2 4 | 8 5 6 
------+-------+------
9 6 1 | 5 3 7 | 2 8 4 
2 8 7 | 4 1 9 | 6 3 5 
3 4 5 | 2 8 6 | 1 7 9 
==================================================
```

---

## 6. Output Screenshot
![Sudoku Solver Output](output/output.png)

---

## 7. Learning Outcomes
- Applied multidimensional spatial constraints ($row, col, 3 \times 3$ sub-matrix) within recursive search loops.
- Mastered state resetting during backtracking rollbacks.
