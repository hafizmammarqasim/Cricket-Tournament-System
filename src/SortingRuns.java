import java.util.Comparator;

public class SortingRuns implements Comparator<Player> {

    @Override
    public int compare(Player o1, Player o2) {
        if (o1 instanceof RunsScored batsman1 && o2 instanceof RunsScored batsman2)
            return Integer.compare(batsman1.getRunsScored(), batsman2.getRunsScored());
        else
            return 0;
        }
}
