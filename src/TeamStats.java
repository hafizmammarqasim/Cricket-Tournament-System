public class TeamStats {
    private int matchesPlayed;
    private int matchesWon;
    private int matchesLost;
    private int runsScored;
    private int wicketsTaken;
    private int points;

    public TeamStats (){

    }
    public TeamStats(TeamStats stats){
        this.matchesPlayed = stats.getMatchesPlayed();
        this.matchesLost = stats.getMatchesLost();
        this.matchesWon = stats.getMatchesWon();
        this.runsScored = stats.getRunsScored();
        this.wicketsTaken = stats.getWicketsTaken();
        this.points = stats.getPoints();
    }
    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points= this.points+ points;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    public int getMatchesWon() {
        return matchesWon;
    }

    public void setMatchesWon(int matchesWon) {
        this.matchesWon = matchesWon;
    }

    public int getMatchesLost() {
        matchesLost = matchesPlayed - matchesWon;
        return matchesLost;
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }


    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

}
