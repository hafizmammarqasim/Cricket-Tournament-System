import java.util.Comparator;

public class SortingWickets implements Comparator<WicketsTaken> {
    public int compare(WicketsTaken p1, WicketsTaken p2){
            return Integer.compare(p2.getWicketsTaken(),p1.getWicketsTaken());
    }
}
