import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SumOfSubsets {

    public static void findSubsets(int[] numbers, int targetSum) {
        Arrays.sort(numbers);
        int totalSum = 0;
        for (int num : numbers) {
            totalSum += num;
        }

        System.out.println("SUM OF SUBSETS PROBLEM USING BACKTRACKING");
        System.out.println("Given Set: " + Arrays.toString(numbers));
        System.out.println("Target Sum: " + targetSum);
        System.out.println("-----------------------------------------");

        List<Integer> currentSubset = new ArrayList<>();
        int[] solutionCount = new int[1];

        backtrack(numbers, 0, 0, totalSum, targetSum, currentSubset, solutionCount);

        System.out.println("-----------------------------------------");
        System.out.println("Total Valid Subsets Found: " + solutionCount[0]);
    }

    private static void backtrack(int[] numbers, int index, int currentSum, int remainingSum, int targetSum,
                                  List<Integer> currentSubset, int[] solutionCount) {
        if (currentSum == targetSum) {
            solutionCount[0]++;
            System.out.println("Solution " + solutionCount[0] + ": " + currentSubset);
            return;
        }

        for (int i = index; i < numbers.length; i++) {
            if (currentSum + numbers[i] > targetSum) {
                break;
            }
            if (currentSum + remainingSum < targetSum) {
                return;
            }

            currentSubset.add(numbers[i]);
            backtrack(numbers, i + 1, currentSum + numbers[i], remainingSum - numbers[i], targetSum, currentSubset, solutionCount);
            currentSubset.remove(currentSubset.size() - 1);
        }
    }

    public static void main(String[] args) {
        int[] numbers = {10, 7, 5, 18, 12, 20, 15};
        int targetSum = 35;

        findSubsets(numbers, targetSum);
    }
}
