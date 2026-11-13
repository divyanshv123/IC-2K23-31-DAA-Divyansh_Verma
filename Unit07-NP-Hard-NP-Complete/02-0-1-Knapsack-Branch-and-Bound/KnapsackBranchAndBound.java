import java.util.*;

public class KnapsackBranchAndBound {

    static class Product {
        int weight, value;
        double ratio;

        Product(int weight, int value) {
            this.weight = weight;
            this.value = value;
            this.ratio = (double) value / weight;
        }
    }

    static class State {
        int level, currentValue, currentWeight;
        double upperBound;

        State(int level, int value, int weight) {
            this.level = level;
            this.currentValue = value;
            this.currentWeight = weight;
        }
    }

    // Calculate the maximum possible profit from a state
    public static double findBound(State state, int count,
                                   int limit, Product[] products) {

        if (state.currentWeight >= limit) {
            return state.currentWeight == limit
                    ? state.currentValue : 0;
        }

        double possibleValue = state.currentValue;
        int usedWeight = state.currentWeight;
        int index = state.level + 1;

        // Add complete items while capacity is available
        while (index < count &&
               usedWeight + products[index].weight <= limit) {

            usedWeight += products[index].weight;
            possibleValue += products[index].value;
            index++;
        }

        // Add a fraction of the next item for the upper bound
        if (index < count) {
            possibleValue +=
                (limit - usedWeight) * products[index].ratio;
        }

        return possibleValue;
    }

    // Solve the 0/1 Knapsack problem
    public static int maximizeValue(Product[] products, int capacity) {

        int count = products.length;

        // Arrange products by profit-to-weight ratio
        Arrays.sort(products,
            (a, b) -> Double.compare(b.ratio, a.ratio));

        // Process the state with the highest bound first
        PriorityQueue<State> pending = new PriorityQueue<>(
            (a, b) -> Double.compare(b.upperBound, a.upperBound)
        );

        State initial = new State(-1, 0, 0);

        initial.upperBound = findBound(
            initial, count, capacity, products
        );

        pending.add(initial);

        int bestValue = 0;

        while (!pending.isEmpty()) {

            State current = pending.poll();

            // Ignore states that cannot improve the answer
            if (current.upperBound <= bestValue ||
                current.level == count - 1) {
                continue;
            }

            int nextItem = current.level + 1;

            // Option 1: Select the next product
            State selected = new State(
                nextItem,
                current.currentValue + products[nextItem].value,
                current.currentWeight + products[nextItem].weight
            );

            if (selected.currentWeight <= capacity) {

                bestValue = Math.max(
                    bestValue, selected.currentValue
                );

                selected.upperBound = findBound(
                    selected, count, capacity, products
                );

                if (selected.upperBound > bestValue) {
                    pending.add(selected);
                }
            }

            // Option 2: Do not select the next product
            State rejected = new State(
                nextItem,
                current.currentValue,
                current.currentWeight
            );

            rejected.upperBound = findBound(
                rejected, count, capacity, products
            );

            if (rejected.upperBound > bestValue) {
                pending.add(rejected);
            }
        }

        return bestValue;
    }

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Enter total number of items: ");
        int count = input.nextInt();

        if (count <= 0) {
            System.out.println("Number of items must be positive.");
            input.close();
            return;
        }

        Product[] products = new Product[count];

        System.out.println("Enter weight and profit for each item:");

        for (int i = 0; i < count; i++) {

            System.out.print("Item " + (i + 1) + ": ");

            int weight = input.nextInt();
            int profit = input.nextInt();

            if (weight <= 0 || profit < 0) {
                System.out.println(
                    "Weight must be positive and profit cannot be negative."
                );
                input.close();
                return;
            }

            products[i] = new Product(weight, profit);
        }

        System.out.print("Enter maximum bag capacity: ");
        int capacity = input.nextInt();

        if (capacity < 0) {
            System.out.println("Capacity cannot be negative.");
            input.close();
            return;
        }

        int answer = maximizeValue(products, capacity);

        System.out.println("\nKnapsack Result");
        System.out.println("---------------------------");
        System.out.println("Number of items : " + count);
        System.out.println("Bag capacity    : " + capacity);
        System.out.println("Maximum profit  : " + answer);

        input.close();
    }
}
