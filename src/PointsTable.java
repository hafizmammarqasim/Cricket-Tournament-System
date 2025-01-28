import java.util.Comparator;

public class PointsTable implements Comparator<Team> {

    public int compare(Team team1,Team team2){
        return Integer.compare(team2.getStats().getPoints(),team1.getStats().getPoints());
    }

}
