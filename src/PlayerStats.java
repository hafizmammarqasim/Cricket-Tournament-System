import java.util.ArrayList;

public class PlayerStats {
    private String jerseyNumber;
    private int wickets;
    private int catchTaken;
    private int totalRuns;
    private int totalMatches;

    public PlayerStats(String jerseyNumber, int wickets, int catchTaken, int totalRuns, int totalMatches) {
        this.jerseyNumber = jerseyNumber;
        this.wickets = wickets;
        this.catchTaken = catchTaken;
        this.totalRuns = totalRuns;
        this.totalMatches = totalMatches;
    }

    public PlayerStats(){

    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets += wickets;
    }

    public int getCatchTaken() {
        return catchTaken;
    }

    public void setCatchTaken(int catchTaken) {
        this.catchTaken += catchTaken;
    }

    public int getTotalRuns() {
        return totalRuns;
    }

    public void setTotalRuns(int totalRuns) {
        this.totalRuns += totalRuns;
    }

    public int getTotalMatches() {
        return totalMatches;
    }

    public void setTotalMatches(int totalMatches) {
        this.totalMatches += totalMatches;
    }
}
