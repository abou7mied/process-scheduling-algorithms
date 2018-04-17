import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class RoundRobin extends AllocationStrategy {


    private int quantum;
    private double avgWaitingTime;
    private double avgTurnAroundTime;
    private double currentTime;
    private boolean shortestRemaining;

    RoundRobin(List<Job> jobs, int quantum) {
        super(jobs);
        this.quantum = quantum;
    }

    RoundRobin(List<Job> jobs, int quantum, boolean shortestRemaining) {
        super(jobs);
        this.quantum = quantum;
        this.shortestRemaining = shortestRemaining;
    }


    public void run() {

        int i = 0;


        ArrayList<Job> clonedJobs = (ArrayList<Job>) ((ArrayList<Job>) jobs).clone();
        sortJobs(clonedJobs);


        while (clonedJobs.size() > 0) {
            Job job = clonedJobs.get(i);

            double time = currentTime;

            if (job.getArrivalTime() > time) {
                if (i + 1 == clonedJobs.size())
                    currentTime += quantum;
                i = ++i % clonedJobs.size();
                continue;
            } else {
                currentTime += quantum;
            }


            job.setCpuTime(Math.max(0, job.getCpuTime() - quantum));

            boolean completed = job.getCpuTime() == 0;

            if (completed) {
                job.setCompletionTime(currentTime);
                clonedJobs.remove(job);
            }

            sortJobs(clonedJobs);

            if (clonedJobs.size() > 0)
                i = (i + (shortestRemaining || completed ? 0 : 1)) % clonedJobs.size();

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

    private void sortJobs(List<Job> jobs) {
        if (!shortestRemaining)
            return;
        jobs.sort(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.getCpuTime() - o2.getCpuTime();
            }
        });
    }

}