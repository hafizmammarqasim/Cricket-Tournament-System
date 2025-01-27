import java.util.Comparator;

public class SortingRuns implements Comparator<RunsScored> {

    @Override
    public int compare(RunsScored o1, RunsScored o2) {
            return Integer.compare(o2.getRunsScored(), o1.getRunsScored());
        }
}
