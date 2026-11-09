import java.io.PrintWriter;
import java.io.IOException;
import java.util.Arrays;

public class ExecutionTime {

    static volatile int resultSink = -1;

    static int linearSearch(int[] arr, int target) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == target) {
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] inputSizes = {100, 1000, 10000, 100000, 1000000};
        int[] repetitions = {10000, 5000, 1000, 100, 10};
        
        System.out.println("LINEAR SEARCH TIME ANALYSIS");

        System.out.printf("%-15s %-15s %-20s%n",
                "Input Size", "Repetitions", "Avg Time (ns)");

        System.out.println("----------------------------------------------");

        try (PrintWriter outputFile = new PrintWriter("results.csv")) {

            outputFile.println("Input Size,Repetitions,Average Time (ns)");

            for (int i = 0; i < inputSizes.length; i++) {

                int size = inputSizes[i];
                int repeat = repetitions[i];

                int[] arr = new int[size];

                for (int j = 0; j < size; j++) {
                    arr[j] = j + 1;
                }

                int target = size;
                long checksum = 0;

                long start = System.nanoTime();

                for (int j = 0; j < repeat; j++) {
                    checksum += linearSearch(arr, target);
                }

                long stop = System.nanoTime();

                resultSink = (int) checksum;

                long duration = stop - start;

                double averageTime = (double) duration / repeat;

                System.out.printf("%-15d %-15d %-20.2f%n",
                        size, repeat, averageTime);

                outputFile.printf("%d,%d,%.2f%n",
                        size, repeat, averageTime);
            }

            System.out.println("----------------------------------------------");
            System.out.println("Results successfully saved to results.csv");

        } catch (IOException e) {
            System.out.println("Error: Unable to create results.csv");
        }
    }
}
