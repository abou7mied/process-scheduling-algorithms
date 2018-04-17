import java.util.Comparator;
import java.util.List;

public class FirstComeFirstServed extends AllocationStrategy {

    double prevCompletionTime;
    double avgWaitingTime;
    double avgTurnAroundTime;

    FirstComeFirstServed(List<Job> jobs) {
        super(jobs);
    }

    public void run() {


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
            count++;
        }

        printResults();

    }

    private void printResults() {
        jobs.sort(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.getProcessId() - o2.getProcessId();
            }
        });
        printHeaderMessage();
        for (Job job : jobs) {
            printJobResult(job);
        }
        printFooterMessage(avgWaitingTime, avgTurnAroundTime);

    }
}