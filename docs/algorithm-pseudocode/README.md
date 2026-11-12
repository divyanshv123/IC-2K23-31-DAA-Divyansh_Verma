# Algorithm Pseudocode Repository

Comprehensive standard algorithmic pseudocode documentation across Units 01 through 07.

## 1. Divide and Conquer
### Merge Sort
```text
Algorithm MergeSort(A, left, right):
    if left < right then:
        mid = left + (right - left) / 2
        MergeSort(A, left, mid)
        MergeSort(A, mid + 1, right)
        Merge(A, left, mid, right)
```

### Quick Sort
```text
Algorithm QuickSort(A, low, high):
    if low < high then:
        p = Partition(A, low, high)
        QuickSort(A, low, p - 1)
        QuickSort(A, p + 1, high)
```

## 2. Greedy Method
### Fractional Knapsack
```text
Algorithm FractionalKnapsack(items, W):
    sort items in descending order of value/weight ratio
    totalValue = 0, currentWeight = 0
    for each item i in items do:
        if currentWeight + i.weight <= W then:
            take full item
        else:
            take fraction (W - currentWeight) / i.weight
            break
    return totalValue
```

## 3. Dynamic Programming
### 0/1 Knapsack
```text
Algorithm ZeroOneKnapsack(values, weights, W, n):
    dp = array[0..n, 0..W] initialized to 0
    for i = 1 to n do:
        for w = 1 to W do:
            if weights[i-1] <= w then:
                dp[i][w] = max(values[i-1] + dp[i-1][w-weights[i-1]], dp[i-1][w])
            else:
                dp[i][w] = dp[i-1][w]
    return dp[n][W]
```

## 4. Backtracking
### N-Queens
```text
Algorithm PlaceQueens(row, n, board):
    if row == n then recordSolution(board)
    for col = 0 to n - 1 do:
        if isSafe(board, row, col) then:
            board[row] = col
            PlaceQueens(row + 1, n, board)
```

## 5. Branch and Bound
### 0/1 Knapsack LCBB
```text
Algorithm KnapsackLCBB(items, W):
    pq = new MaxPriorityQueue(ordered by upper bound)
    pq.insert(root)
    while not pq.isEmpty() do:
        curr = pq.extractMax()
        branch into left (include) and right (exclude)
        prune branches where bound <= currentMaxProfit
```
