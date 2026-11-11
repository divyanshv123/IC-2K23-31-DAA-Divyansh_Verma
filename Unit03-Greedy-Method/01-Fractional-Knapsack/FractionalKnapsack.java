import java.util.Arrays;
import java.util.Comparator;

public class FractionalKnapsack {

    static class Item {
        int id;
        double weight;
        double value;
        double ratio;

        Item(int id, double weight, double value) {
            this.id = id;
            this.weight = weight;
            this.value = value;
            this.ratio = value / weight;
        }
    }

    public static double getMaxValue(Item[] items, double capacity) {
        Arrays.sort(items, new Comparator<Item>() {
            public int compare(Item a, Item b) {
                return Double.compare(b.ratio, a.ratio);
            }
        });

        double totalValue = 0.0;
        double currentCapacity = capacity;

        System.out.printf("%-8s | %-10s | %-10s | %-12s | %-14s%n",
                "Item ID", "Weight", "Value", "Ratio (V/W)", "Fraction Taken");
        System.out.println("------------------------------------------------------------------");

        for (int i = 0; i < items.length; i++) {
            Item item = items[i];

            if (currentCapacity >= item.weight) {
                currentCapacity -= item.weight;
                totalValue += item.value;
                System.out.printf("%-8d | %-10.2f | %-10.2f | %-12.2f | %-14.2f%n",
                        item.id, item.weight, item.value, item.ratio, 1.0);
            } else if (currentCapacity > 0) {
                double fraction = currentCapacity / item.weight;
                totalValue += item.value * fraction;
                System.out.printf("%-8d | %-10.2f | %-10.2f | %-12.2f | %-14.2f%n",
                        item.id, item.weight, item.value, item.ratio, fraction);
                currentCapacity = 0;
                break;
            }
        }

        return totalValue;
    }

    public static void main(String[] args) {
        Item[] items = {
            new Item(1, 10, 60),
            new Item(2, 20, 100),
            new Item(3, 30, 120)
        };

        double knapsackCapacity = 50.0;

        System.out.println("FRACTIONAL KNAPSACK USING GREEDY METHOD");
        System.out.println("Knapsack Capacity: " + knapsackCapacity);
        System.out.println("------------------------------------------------------------------");

        double maxProfit = getMaxValue(items, knapsackCapacity);

        System.out.println("------------------------------------------------------------------");
        System.out.printf("Maximum Profit Obtained: %.2f%n", maxProfit);
    }
}
