import java.util.Comparator;
import java.util.List;

public class ShortestJobNext extends FirstComeFirstServed {

    ShortestJobNext(List<Job> jobs) {
        super(jobs);
    }

    @Override
    public void run() {

        jobs.sort(new Comparator<Job>() {
            @Override
            public int compare(Job o1, Job o2) {
                return o1.getCpuTime() - o2.getCpuTime();
            }
        });

        super.run();

    }


}
