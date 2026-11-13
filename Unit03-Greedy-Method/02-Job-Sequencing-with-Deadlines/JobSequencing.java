import java.util.Arrays;
import java.util.Scanner;

class Task {
    int jobNo;
    int dueDate;
    int earning;

    Task(int jobNo, int dueDate, int earning) {
        this.jobNo = jobNo;
        this.dueDate = dueDate;
        this.earning = earning;
    }
}

public class JobSequencing {

    static void scheduleJobs(Task[] tasks) {

        Arrays.sort(tasks, (a, b) -> Integer.compare(b.earning, a.earning));

        int lastDay = 0;

        for (Task t : tasks) {
            lastDay = Math.max(lastDay, t.dueDate);
        }

        int[] schedule = new int[lastDay + 1];
        Arrays.fill(schedule, -1);

        int profit = 0;
        int completed = 0;

        for (Task t : tasks) {

            for (int day = t.dueDate; day >= 1; day--) {

                if (schedule[day] == -1) {
                    schedule[day] = t.jobNo;
                    profit += t.earning;
                    completed++;
                    break;
                }
            }
        }

        System.out.println("\n========== JOB SCHEDULE ==========");

        System.out.printf("%-12s %-12s%n", "Time Slot", "Selected Job");
        System.out.println("----------------------------------");

        for (int day = 1; day <= lastDay; day++) {

            if (schedule[day] != -1) {
                System.out.printf("%-12d Job %d%n", day, schedule[day]);
            } else {
                System.out.printf("%-12d %s%n", day, "Empty");
            }
        }

        System.out.println("----------------------------------");
        System.out.println("Jobs completed : " + completed);
        System.out.println("Total profit   : " + profit);
        System.out.println("==================================");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("       JOB SCHEDULING SYSTEM");
        System.out.println("==================================");

        System.out.print("Enter total number of jobs: ");
        int count = sc.nextInt();

        if (count <= 0) {
            System.out.println("Please enter a valid number of jobs.");
            sc.close();
            return;
        }

        Task[] tasks = new Task[count];

        System.out.println("\nEnter job details below:");

        for (int i = 0; i < count; i++) {

            System.out.println("\nJob " + (i + 1));

            System.out.print("Enter deadline: ");
            int deadline = sc.nextInt();

            System.out.print("Enter profit: ");
            int earning = sc.nextInt();

            if (deadline <= 0 || earning < 0) {
                System.out.println("Invalid deadline or profit.");
                sc.close();
                return;
            }

            tasks[i] = new Task(i + 1, deadline, earning);
        }

        scheduleJobs(tasks);

        sc.close();
    }
}
