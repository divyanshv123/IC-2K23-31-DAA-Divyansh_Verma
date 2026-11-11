import java.util.Arrays;
import java.util.Comparator;

public class JobSequencing {

    static class Job {
        String id;
        int deadline;
        int profit;

        Job(String id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }

    public static void scheduleJobs(Job[] jobs) {
        Arrays.sort(jobs, new Comparator<Job>() {
            public int compare(Job a, Job b) {
                return b.profit - a.profit;
            }
        });

        int maxDeadline = 0;
        for (int i = 0; i < jobs.length; i++) {
            if (jobs[i].deadline > maxDeadline) {
                maxDeadline = jobs[i].deadline;
            }
        }

        String[] slots = new String[maxDeadline + 1];
        boolean[] filled = new boolean[maxDeadline + 1];

        int totalProfit = 0;
        int jobCount = 0;

        for (int i = 0; i < jobs.length; i++) {
            for (int j = jobs[i].deadline; j > 0; j--) {
                if (!filled[j]) {
                    filled[j] = true;
                    slots[j] = jobs[i].id;
                    totalProfit += jobs[i].profit;
                    jobCount++;
                    break;
                }
            }
        }

        System.out.println("Scheduled Job Sequence:");
        for (int i = 1; i <= maxDeadline; i++) {
            if (filled[i]) {
                System.out.println("Slot " + i + ": " + slots[i]);
            }
        }

        System.out.println("\nTotal Jobs Scheduled: " + jobCount);
        System.out.println("Total Maximized Profit: " + totalProfit);
    }

    public static void main(String[] args) {
        Job[] jobs = {
            new Job("J1", 2, 100),
            new Job("J2", 1, 19),
            new Job("J3", 2, 27),
            new Job("J4", 1, 25),
            new Job("J5", 3, 15)
        };

        System.out.println("JOB SEQUENCING WITH DEADLINES (GREEDY METHOD)");
        System.out.println("--------------------------------------------");

        System.out.printf("%-6s | %-10s | %-8s%n", "Job ID", "Deadline", "Profit");
        System.out.println("--------------------------------------------");
        for (int i = 0; i < jobs.length; i++) {
            System.out.printf("%-6s | %-10d | %-8d%n", jobs[i].id, jobs[i].deadline, jobs[i].profit);
        }
        System.out.println("--------------------------------------------");

        scheduleJobs(jobs);
    }
}
