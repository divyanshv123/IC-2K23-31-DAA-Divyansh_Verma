public class NQueensSolver {

    public static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col) {
                return false;
            }
            if (Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    public static int solveNQueens(int n, boolean printFirstSolution) {
        int[] board = new int[n];
        int[] solutionCount = new int[1];
        boolean[] printed = new boolean[1];

        placeQueens(0, n, board, solutionCount, printed, printFirstSolution);
        return solutionCount[0];
    }

    private static void placeQueens(int row, int n, int[] board, int[] solutionCount, boolean[] printed, boolean printFirstSolution) {
        if (row == n) {
            solutionCount[0]++;
            if (printFirstSolution && !printed[0]) {
                printBoard(board, n);
                printed[0] = true;
            }
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                placeQueens(row + 1, n, board, solutionCount, printed, printFirstSolution);
            }
        }
    }

    public static void printBoard(int[] board, int n) {
        System.out.println("Board Configuration (First Valid Placement):");
        for (int i = 0; i < n; i++) {
            System.out.print("  Row " + i + ": ");
            for (int j = 0; j < n; j++) {
                if (board[i] == j) {
                    System.out.print("[Q] ");
                } else {
                    System.out.print("[.] ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        System.out.println("N-QUEENS PROBLEM USING BACKTRACKING");
        System.out.println("==================================================");

        System.out.println("Solving 4-Queens Problem:");
        int solutions4 = solveNQueens(4, true);
        System.out.println("Total Distinct Solutions for 4-Queens: " + solutions4);

        System.out.println("\n--------------------------------------------------\n");

        System.out.println("Solving 8-Queens Problem:");
        int solutions8 = solveNQueens(8, true);
        System.out.println("Total Distinct Solutions for 8-Queens: " + solutions8);

        System.out.println("==================================================");
    }
}
