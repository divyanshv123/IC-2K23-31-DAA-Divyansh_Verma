public class BinarySearchIterative {

    // Iterative binary search
    static int binarySearch(int[] arr, int target) {
        int start = 0;
        int end = arr.length - 1;

        while (start <= end) {
            // Find middle index (prevents integer overflow)
            int mid = start + (end - start) / 2;

            if (arr[mid] == target) {
                return mid; // Key found
            } else if (arr[mid] < target) {
                start = mid + 1; // Search right half
            } else {
                end = mid - 1; // Search left half
            }
        }

        return -1; // Key not found
    }

    public static void main(String[] args) {
        int[] arr = { 2, 4, 6, 8, 10, 12 };
        int target = 8;

        int result = binarySearch(arr, target);

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found");
        }
    }
}
