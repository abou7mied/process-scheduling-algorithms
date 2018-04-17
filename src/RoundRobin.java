import java.util.ArrayList;
import java.util.List;

public class RoundRobin extends AllocationStrategy {


    private int quantum;
    private double avgWaitingTime;
    private double avgTurnAroundTime;
    private double currentTime;

    RoundRobin(List<Job> jobs, int quantum) {
        super(jobs);
        this.quantum = quantum;
    }


    public void run() {

        int i = 0;

        ArrayList<Job> clonedJobs = (ArrayList<Job>) ((ArrayList<Job>) jobs).clone();


        while (clonedJobs.size() > 0) {
            Job job = clonedJobs.get(i);

            double time = currentTime;

            if (job.getArrivalTime() > time)
                continue;

            currentTime += quantum;

            job.setCpuTime(Math.max(0, job.getCpuTime() - quantum));

            boolean completed = job.getCpuTime() == 0;

            if (completed) {
                job.setCompletionTime(currentTime);
                clonedJobs.remove(job);
            }

            if (clonedJobs.size() > 0)
                i = (i + (completed ? 0 : 1)) % clonedJobs.size();

        }

        printHeaderMessage();
        for (Job job : jobs) {
            job.turnAroundTime = job.completionTime - job.getArrivalTime();
            job.waitingTime = job.turnAroundTime - job.getOriginalCpuTime();
            avgWaitingTime += job.waitingTime;
            avgTurnAroundTime += job.turnAroundTime;
            printJobResult(job);
        }

        printFooterMessage(avgWaitingTime, avgTurnAroundTime);


    }
}