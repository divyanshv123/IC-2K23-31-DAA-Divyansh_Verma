import java.util.Scanner;

public class NQueensSolver {

    static int size;
    static int[] queenPosition;
    static int totalWays = 0;
    static boolean boardDisplayed = false;

    // Check whether a queen can be placed safely
    public static boolean checkPosition(int row, int column) {

        for (int previousRow = 0; previousRow < row; previousRow++) {

            int previousColumn = queenPosition[previousRow];

            // Check same column and diagonal positions
            if (previousColumn == column ||
                Math.abs(previousColumn - column) == row - previousRow) {
                return false;
            }
        }

        return true;
    }

    // Display the chessboard
    public static void displayBoard() {

        for (int row = 0; row < size; row++) {

            for (int column = 0; column < size; column++) {

                if (queenPosition[row] == column) {
                    System.out.print("Q  ");
                } else {
                    System.out.print(".  ");
                }
            }

            System.out.println();
        }
    }

    // Solve the N-Queens problem using backtracking
    public static void placeQueens(int row) {

        // All queens have been placed successfully
        if (row == size) {

            totalWays++;

            if (!boardDisplayed) {
                System.out.println("\nFirst valid arrangement:");
                displayBoard();
                boardDisplayed = true;
            }

            return;
        }

        // Try placing a queen in every column
        for (int column = 0; column < size; column++) {

            if (checkPosition(row, column)) {

                queenPosition[row] = column;

                placeQueens(row + 1);
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the size of chessboard (1-15): ");
        size = sc.nextInt();

        if (size < 1 || size > 15) {
            System.out.println("Invalid board size!");
            sc.close();
            return;
        }

        queenPosition = new int[size];

        placeQueens(0);

        if (totalWays == 0) {
            System.out.println("\nNo valid arrangement exists for N = " + size);
        } else {
            System.out.println("\nTotal number of solutions: " + totalWays);
        }

        sc.close();
    }
}
