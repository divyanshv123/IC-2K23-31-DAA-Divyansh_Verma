public class BinarySearchRecursive {

    // Recursive binary search
    static int binarySearch(int[] arr, int start, int end, int target) {
        if (start > end) {
            return -1; // Base case: element not found
        }

        int mid = start + (end - start) / 2;

        if (arr[mid] == target) {
            return mid; // Base case: element found
        } else if (arr[mid] < target) {
            return binarySearch(arr, mid + 1, end, target); // Search right half
        } else {
            return binarySearch(arr, start, mid - 1, target); // Search left half
        }
    }

    public static void main(String[] args) {
        int[] arr = {2, 4, 6, 8, 10, 12};
        int target = 8;

        int result = binarySearch(arr, 0, arr.length - 1, target);

        if (result != -1) {
            System.out.println("Element found at index: " + result);
        } else {
            System.out.println("Element not found");
        }
    }
}
