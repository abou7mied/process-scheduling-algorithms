import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Example {
    public static void main(String[] args) {

        System.out.println("Select the scheduling Strategy:");
        System.out.println("[1] First come first served");
        System.out.println("[2] Shortest Remaining Time");
        System.out.println("[3] Round Robin");
        System.out.println("[4] Shortest Job Next");
        System.out.println("[5] Fair-Share");


        Scanner scanner = new Scanner(System.in);

        int selectedStrategy = scanner.nextInt();

        BufferedReader br = null;
        try {
            String sCurrentLine;
            br = new BufferedReader(new FileReader("testing.txt"));
            List<Job> jobList = new ArrayList<>();
            while ((sCurrentLine = br.readLine()) != null) {
                String a[] = sCurrentLine.split(",");
                int processId = new Integer(a[0]);
                int arrivalTime = new Integer(a[1]);
                int cpuTime = new Integer(a[2]);
                Job job = new Job(processId, arrivalTime, cpuTime);
                jobList.add(job);
            }

            int quantum;
            AllocationStrategy strategy = null;

            switch (selectedStrategy) {
                case 1:
                    strategy = new FirstComeFirstServed(jobList);
                    break;

                case 2:
                    System.out.println("Enter quantum: ");
                    quantum = scanner.nextInt();
                    strategy = new RoundRobin(jobList, quantum, true);
                    break;

                case 3:
                    System.out.println("Enter quantum: ");
                    quantum = scanner.nextInt();
                    strategy = new RoundRobin(jobList, quantum);
                    break;

                case 4:
                    strategy = new ShortestJobNext(jobList);
                    break;

                case 5:
                    System.out.println("Enter quantum: ");
                    quantum = scanner.nextInt();
                    strategy = new RoundRobin(jobList, quantum);
                    break;

            }

            if (strategy != null)
                strategy.run();


        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }


    }
}