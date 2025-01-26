import java.util.ArrayList;

public class Schedule {
    private ArrayList<Match> matches = new ArrayList<>();
    private ArrayList<Ground> venue = new ArrayList<>();

    public Schedule(){

    }

    public Schedule(Match match, Ground venue, String date){
        addNewMatch(match);
        this.venue.add(venue);
    }

    public Schedule(Match match, Ground venue){
        addNewMatch(match);
        this.venue.add(venue);
    }

    public Schedule(Schedule s){
        this.matches = s.matches;
    }

    public void addNewMatch(Match match){
        matches.add(match);
    }

    public ArrayList<Match> getMatches(){
        return matches;
    }

    public void displayMatches(){
        for(Match match: matches)
            System.out.println(match.displayMatch());
    }

}
