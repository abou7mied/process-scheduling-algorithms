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


        Scanner scanner = new Scanner(System.in);

        int strategy = scanner.nextInt();

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


            switch (strategy) {
                case 1:
                    FirstComeFirstServed firstComeFirstServed = new FirstComeFirstServed(jobList);
                    firstComeFirstServed.run();
                    break;

                case 3:
                    System.out.println("Enter quantum: ");
                    int quantum = scanner.nextInt();
                    RoundRobin roundRobin = new RoundRobin(jobList, quantum);
                    roundRobin.run();
                    break;

            }



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