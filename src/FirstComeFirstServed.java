import java.util.List;

public class FirstComeFirstServed extends AllocationStrategy {

    double prevCompletionTime;
    double avgWaitingTime;
    double avgTurnAroundTime;

    FirstComeFirstServed(List<Job> jobs) {
        super(jobs);
    }

    public void run() {

        printHeaderMessage();

        int count = 0;

        for (Job job : jobs) {
            if (count == 0) {
                job.completionTime = job.getArrivalTime() + job.getCpuTime();
            } else {
                job.completionTime = prevCompletionTime + job.getCpuTime();
            }

            prevCompletionTime = job.completionTime;
            job.turnAroundTime = prevCompletionTime - job.getArrivalTime();
            job.waitingTime = job.turnAroundTime - job.getCpuTime();
            avgWaitingTime += job.waitingTime;
            avgTurnAroundTime += job.turnAroundTime;
            printJobResult(job);
            count++;
        }

        printFooterMessage(avgWaitingTime, avgTurnAroundTime);


    }
}