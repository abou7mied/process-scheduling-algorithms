public class Job {
    public double completionTime;
    public double waitingTime;
    public double turnAroundTime;

    private int originalCpuTime;
    private int arrivalTime, cpuTime, processId;


    public Job(int processId, int arrivalTime, int cpuTime) {
        this.processId = processId;
        this.arrivalTime = arrivalTime;
        this.originalCpuTime = cpuTime;
        this.cpuTime = cpuTime;
    }

    public void setCompletionTime(double completionTime) {
        this.completionTime = completionTime;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getOriginalCpuTime() {
        return originalCpuTime;
    }

    public int getCpuTime() {
        return cpuTime;
    }

    public void setCpuTime(int cpuTime) {
        this.cpuTime = cpuTime;
    }

    public int getProcessId() {
        return processId;
    }

}