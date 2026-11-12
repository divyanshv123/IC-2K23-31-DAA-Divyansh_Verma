public class ConstraintSatisfactionSolver {

    static class Metrics {
        long exploredStates = 0;
        int solutionCount = 0;
    }

    public static boolean isSafe(int[] board, int row, int col) {
        for (int i = 0; i < row; i++) {
            if (board[i] == col || Math.abs(board[i] - col) == Math.abs(i - row)) {
                return false;
            }
        }
        return true;
    }

    public static void backtrackNQueens(int row, int n, int[] board, Metrics metrics) {
        metrics.exploredStates++;
        if (row == n) {
            metrics.solutionCount++;
            return;
        }

        for (int col = 0; col < n; col++) {
            if (isSafe(board, row, col)) {
                board[row] = col;
                backtrackNQueens(row + 1, n, board, metrics);
            }
        }
    }

    public static void exhaustiveSearch4Queens(int[] board, int row, Metrics metrics) {
        metrics.exploredStates++;
        if (row == 4) {
            boolean valid = true;
            for (int i = 0; i < 4; i++) {
                for (int j = i + 1; j < 4; j++) {
                    if (board[i] == board[j] || Math.abs(board[i] - board[j]) == Math.abs(i - j)) {
                        valid = false;
                        break;
                    }
                }
                if (!valid) {
                    break;
                }
            }
            if (valid) {
                metrics.solutionCount++;
            }
            return;
        }

        for (int col = 0; col < 4; col++) {
            board[row] = col;
            exhaustiveSearch4Queens(board, row + 1, metrics);
        }
    }

    public static void main(String[] args) {
        System.out.println("=========================================================================================");
        System.out.println("                   CONSTRAINT SATISFACTION BENCHMARK SYSTEM                              ");
        System.out.println("            (Comparative Search Space Analysis: Backtracking vs. Exhaustive)             ");
        System.out.println("=========================================================================================");

        Metrics exhMetrics = new Metrics();
        int[] exhBoard = new int[4];
        long startExh = System.nanoTime();
        exhaustiveSearch4Queens(exhBoard, 0, exhMetrics);
        long endExh = System.nanoTime();
        double exhTime = (endExh - startExh) / 1000000.0;

        Metrics bt4Metrics = new Metrics();
        int[] bt4Board = new int[4];
        long startBt4 = System.nanoTime();
        backtrackNQueens(0, 4, bt4Board, bt4Metrics);
        long endBt4 = System.nanoTime();
        double bt4Time = (endBt4 - startBt4) / 1000000.0;

        System.out.println("EXHAUSTIVE SEARCH VS. BACKTRACKING PRUNING (N = 4):");
        System.out.printf("%-18s | %-16s | %-16s | %-14s%n",
                "Strategy", "Explored States", "Solutions Found", "Time (ms)");
        System.out.println("-----------------------------------------------------------------------------------------");
        System.out.printf("%-18s | %-16d | %-16d | %-14.4f%n",
                "Exhaustive Brute", exhMetrics.exploredStates, exhMetrics.solutionCount, exhTime);
        System.out.printf("%-18s | %-16d | %-16d | %-14.4f%n",
                "Backtracking Prune", bt4Metrics.exploredStates, bt4Metrics.solutionCount, bt4Time);
        double pruningRatio4 = (1.0 - (double) bt4Metrics.exploredStates / exhMetrics.exploredStates) * 100.0;
        System.out.printf("State Space Reduction: %.2f%%%n", pruningRatio4);

        System.out.println("\n-----------------------------------------------------------------------------------------\n");

        System.out.println("SCALABILITY ANALYSIS ACROSS PROBLEM SIZES (BACKTRACKING):");
        System.out.printf("%-6s | %-16s | %-18s | %-16s | %-14s%n",
                "N", "Theoretical N^N", "Explored Nodes", "Solutions Found", "Time (ms)");
        System.out.println("-----------------------------------------------------------------------------------------");

        int[] testN = {4, 6, 8, 10};
        for (int n : testN) {
            Metrics m = new Metrics();
            int[] b = new int[n];
            long start = System.nanoTime();
            backtrackNQueens(0, n, b, m);
            long end = System.nanoTime();
            double elapsed = (end - start) / 1000000.0;
            long theoretical = (long) Math.pow(n, n);

            System.out.printf("%-6d | %-16d | %-18d | %-16d | %-14.4f%n",
                    n, theoretical, m.exploredStates, m.solutionCount, elapsed);
        }

        System.out.println("=========================================================================================");
    }
}
