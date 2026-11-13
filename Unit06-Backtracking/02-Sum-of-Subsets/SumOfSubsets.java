import java.util.*;

public class SumOfSubsets {

    static int[] numbers;
    static int totalElements;
    static int requiredSum;
    static int foundCount = 0;

    // Find subsets having the required sum
    public static void findSubsets(int position, int sum, ArrayList<Integer> selected) {

        // A valid subset is found
        if (sum == requiredSum) {
            foundCount++;

            System.out.println("Solution " + foundCount + ": " + selected);
            return;
        }

        // Stop searching if all elements are checked
        // or the current sum exceeds the target
        if (position == totalElements || sum > requiredSum) {
            return;
        }

        // Choice 1: Include the current element
        selected.add(numbers[position]);

        findSubsets(position + 1,
                    sum + numbers[position],
                    selected);

        // Backtrack by removing the last selected element
        selected.remove(selected.size() - 1);

        // Choice 2: Exclude the current element
        findSubsets(position + 1, sum, selected);
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("How many elements are there? ");
        totalElements = input.nextInt();

        if (totalElements <= 0) {
            System.out.println("Please enter a positive number of elements.");
            input.close();
            return;
        }

        numbers = new int[totalElements];

        System.out.println("Enter the elements (positive numbers only):");

        for (int i = 0; i < totalElements; i++) {
            numbers[i] = input.nextInt();

            if (numbers[i] <= 0) {
                System.out.println("All elements must be positive.");
                input.close();
                return;
            }
        }

        System.out.print("Enter the desired sum: ");
        requiredSum = input.nextInt();

        if (requiredSum <= 0) {
            System.out.println("Target sum must be positive.");
            input.close();
            return;
        }

        System.out.println("\nSubsets matching the target sum (" + requiredSum + "):");

        findSubsets(0, 0, new ArrayList<>());

        if (foundCount == 0) {
            System.out.println("No matching subset exists.");
        } else {
            System.out.println("\nTotal subsets found: " + foundCount);
        }

        input.close();
    }
}
