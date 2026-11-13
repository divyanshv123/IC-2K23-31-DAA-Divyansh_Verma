public class ExhaustiveSearch {

    public static void main(String[] args) {

        int size = 4;

        // Cost of assigning each job to each worker
        int[][] expenses = {
            {9, 2, 7, 8},
            {6, 4, 3, 7},
            {5, 8, 1, 8},
            {7, 6, 9, 4}
        };

        int[] arrangement = {0, 1, 2, 3};
        int[] finalAssignment = new int[size];

        int minimumCost = Integer.MAX_VALUE;
        int combinations = 0;

        // Generate and check every possible assignment
        do {
            combinations++;

            int currentCost = 0;

            for (int worker = 0; worker < size; worker++) {
                currentCost += expenses[worker][arrangement[worker]];
            }

            // Save the assignment if its cost is lower
            if (currentCost < minimumCost) {

                minimumCost = currentCost;

                for (int i = 0; i < size; i++) {
                    finalAssignment[i] = arrangement[i];
                }
            }

        } while (nextArrangement(arrangement));

        // Display the cost matrix
        System.out.println("\nAssignment Cost Table:");
        System.out.println("----------------------");

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%4d", expenses[i][j]);
            }
            System.out.println();
        }

        // Display the result
        System.out.println("\nTotal arrangements checked: " + combinations);
        System.out.println("Minimum assignment cost: " + minimumCost);

        System.out.println("\nFinal Job Allocation:");
        System.out.println("---------------------");

        for (int i = 0; i < size; i++) {
            int assignedJob = finalAssignment[i];

            System.out.println(
                "Worker " + (i + 1) +
                "  ->  Job " + (assignedJob + 1) +
                "  | Cost: " + expenses[i][assignedJob]
            );
        }
    }

    // Generate the next permutation in lexicographical order
    public static boolean nextArrangement(int[] arr) {

        int i = arr.length - 2;

        while (i >= 0 && arr[i] >= arr[i + 1]) {
            i--;
        }

        if (i < 0) {
            return false;
        }

        int j = arr.length - 1;

        while (arr[j] <= arr[i]) {
            j--;
        }

        // Swap the two elements
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;

        // Reverse the remaining elements
        int left = i + 1;
        int right = arr.length - 1;

        while (left < right) {
            temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }

        return true;
    }
}
