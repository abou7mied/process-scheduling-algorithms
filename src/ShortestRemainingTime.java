import java.util.List;

public class ShortestRemainingTime extends RoundRobin {
    public ShortestRemainingTime(List<Job> jobList, int quantum) {
        super(jobList, quantum, true);
    }

    public void run(List<Job> jobList) {

    }
}
