import java.util.Random;

public class SortingPerformanceComparison {

    public static void merge(int[] array, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;

        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];

        for (int i = 0; i < n1; i++) {
            leftArray[i] = array[left + i];
        }
        for (int j = 0; j < n2; j++) {
            rightArray[j] = array[mid + 1 + j];
        }

        int i = 0;
        int j = 0;
        int k = left;

        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                array[k] = leftArray[i];
                i++;
            } else {
                array[k] = rightArray[j];
                j++;
            }
            k++;
        }

        while (i < n1) {
            array[k] = leftArray[i];
            i++;
            k++;
        }

        while (j < n2) {
            array[k] = rightArray[j];
            j++;
            k++;
        }
    }

    public static void mergeSort(int[] array, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(array, left, mid);
            mergeSort(array, mid + 1, right);
            merge(array, left, mid, right);
        }
    }

    public static int partition(int[] array, int low, int high) {
        int pivot = array[high];
        int i = low - 1;

        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }

    public static void quickSort(int[] array, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(array, low, high);
            quickSort(array, low, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, high);
        }
    }

    public static void heapify(int[] array, int size, int index) {
        int largest = index;
        int left = 2 * index + 1;
        int right = 2 * index + 2;

        if (left < size && array[left] > array[largest]) {
            largest = left;
        }

        if (right < size && array[right] > array[largest]) {
            largest = right;
        }

        if (largest != index) {
            int swap = array[index];
            array[index] = array[largest];
            array[largest] = swap;
            heapify(array, size, largest);
        }
    }

    public static void heapSort(int[] array) {
        int size = array.length;

        for (int i = size / 2 - 1; i >= 0; i--) {
            heapify(array, size, i);
        }

        for (int i = size - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(array, i, 0);
        }
    }

    public static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }

    public static int[] generateRandomArray(int size, Random random) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(100000);
        }
        return array;
    }

    public static void main(String[] args) {
        int[] inputSizes = {5000, 10000, 20000, 40000, 80000};
        Random random = new Random(42);

        System.out.println("==========================================================================");
        System.out.println("              SORTING PERFORMANCE COMPARISON SYSTEM                       ");
        System.out.println("       (Comparison of Merge Sort, Quick Sort, and Heap Sort)              ");
        System.out.println("==========================================================================");
        System.out.printf("%-12s | %-16s | %-16s | %-16s | %-8s%n",
                "Input Size", "Merge Sort (ms)", "Quick Sort (ms)", "Heap Sort (ms)", "Status");
        System.out.println("--------------------------------------------------------------------------");

        for (int size : inputSizes) {
            int[] baseArray = generateRandomArray(size, random);

            int[] mergeArray = baseArray.clone();
            int[] quickArray = baseArray.clone();
            int[] heapArray = baseArray.clone();

            long startMerge = System.nanoTime();
            mergeSort(mergeArray, 0, mergeArray.length - 1);
            long endMerge = System.nanoTime();
            double mergeTime = (endMerge - startMerge) / 1000000.0;

            long startQuick = System.nanoTime();
            quickSort(quickArray, 0, quickArray.length - 1);
            long endQuick = System.nanoTime();
            double quickTime = (endQuick - startQuick) / 1000000.0;

            long startHeap = System.nanoTime();
            heapSort(heapArray);
            long endHeap = System.nanoTime();
            double heapTime = (endHeap - startHeap) / 1000000.0;

            boolean verified = isSorted(mergeArray) && isSorted(quickArray) && isSorted(heapArray);
            String status = verified ? "PASSED" : "FAILED";

            System.out.printf("%-12d | %-16.3f | %-16.3f | %-16.3f | %-8s%n",
                    size, mergeTime, quickTime, heapTime, status);
        }

        System.out.println("==========================================================================");
    }
}
