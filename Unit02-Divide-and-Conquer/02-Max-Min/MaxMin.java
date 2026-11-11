public class MaxMin {

    static class Result {
        int min;
        int max;

        Result(int min, int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static Result findMaxMin(int[] array, int low, int high) {
        if (low == high) {
            return new Result(array[low], array[low]);
        }

        if (high == low + 1) {
            if (array[low] < array[high]) {
                return new Result(array[low], array[high]);
            } else {
                return new Result(array[high], array[low]);
            }
        }

        int mid = low + (high - low) / 2;

        Result leftResult = findMaxMin(array, low, mid);
        Result rightResult = findMaxMin(array, mid + 1, high);

        int overallMin = Math.min(leftResult.min, rightResult.min);
        int overallMax = Math.max(leftResult.max, rightResult.max);

        return new Result(overallMin, overallMax);
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = {1000, 11, 445, 1, 330, 3000};

        System.out.println("FIND MAXIMUM AND MINIMUM (DIVIDE AND CONQUER)");
        System.out.println("---------------------------------------------");

        System.out.print("Array Elements: ");
        printArray(numbers);

        Result result = findMaxMin(numbers, 0, numbers.length - 1);

        System.out.println("Minimum Element: " + result.min);
        System.out.println("Maximum Element: " + result.max);
    }
}
