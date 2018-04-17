import java.util.List;



public abstract class AllocationStrategy {
    protected List<Job> jobs;

    AllocationStrategy(List<Job> jobs) {
        super();
        this.jobs = jobs;
    }

    public abstract void run();

    void printHeaderMessage(){
        System.out.println("============================================ ");
        System.out.println("Process ID\t| Turnaround time\t| Waiting time ");
        System.out.println("============================================ ");

    }

    void printFooterMessage(double avgWaitingTime, double avgTurnAroundTime){
        System.out.println("===============================================");
        System.out.println("Avg waiting time: " + avgWaitingTime / jobs.size());
        System.out.println("===============================================");
        System.out.println("Avg turn around time: " + avgTurnAroundTime / jobs.size());
        System.out.println("===============================================");
    }

    void printJobResult(Job job){
        System.out.println(" " + job.getProcessId() + "\t\t\t| " + " " + job.turnAroundTime + "\t\t\t\t| " + " " + job.waitingTime + " ");
        System.out.println("----------------------------------------");
    }
}