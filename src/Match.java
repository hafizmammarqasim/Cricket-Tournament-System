public class Match{
    private Team team1;
    private Team team2;
    private String result = "Not Played Yet";
    private Ground venue;
    private String date;
    private int Team1Runs;
    private int Team2Runs;
    private int Team1WicketsTaken;
    private int Team2WicketsTaken;
    private int Team1WicketsFallen;
    private int Team2WicketsFallen;
    private boolean playStatus = false;

    public Match(Team team1, Team team2, Ground venue) {
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
    }

    public Match(Team team1, Team team2, Ground venue, String date) {
        this.team1 = team1;
        this.team2 = team2;
        this.venue = venue;
        this.date = date;
    }

    public Match(int Team1Runs, int Team2Runs, int Team1WicketsFallen, int Team2WicketsFallen, int Team1WicketsTaken,int Team2WicketsTaken, String result, boolean playStatus){
        this.result = result;
        this.Team1Runs = Team1Runs;
        this.Team2Runs = Team2Runs;
        this.Team1WicketsTaken = Team1WicketsTaken;
        this.Team2WicketsTaken = Team2WicketsTaken;
        this.Team1WicketsFallen = Team1WicketsFallen;
        this.Team2WicketsFallen = Team2WicketsFallen;
        this.playStatus = playStatus;
    }

    public int getTeam1Runs() {
        return Team1Runs;
    }

    public void setTeam1Runs(int team1Runs) {
        Team1Runs = team1Runs;
    }

    public int getTeam2Runs() {
        return Team2Runs;
    }

    public void setTeam2Runs(int team2Runs) {
        Team2Runs = team2Runs;
    }

    public int getTeam1WicketsFallen() {
        return Team1WicketsFallen;
    }

    public void setTeam1WicketsFallen(int team1Wickets) {
        Team1WicketsFallen = team1Wickets;
    }

    public int getTeam2WicketsFallen() {
        return Team2WicketsFallen;
    }

    public void setTeam2WicketsFallen(int team2Wickets) {
        Team2WicketsFallen = team2Wickets;
    }

    public boolean isPlayStatus() {
        return playStatus;
    }

    public boolean getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(boolean playStatus) {
        this.playStatus = playStatus;
    }

    public void addDate(String date){
        this.date = date;
    }

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Ground getVenue() {
        return venue;
    }

    public void setVenue(Ground venue) {
        this.venue = venue;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Team getTeam1(){
        return team1;
    }

    public Team getTeam2(){
        return team2;
    }

    public int getTeam1WicketsTaken() {
        return Team1WicketsTaken;
    }

    public void setTeam1WicketsTaken(int team1WicketsTaken) {
        Team1WicketsTaken = team1WicketsTaken;
    }

    public int getTeam2WicketsTaken() {
        return Team2WicketsTaken;
    }

    public void setTeam2WicketsTaken(int team2WicketsTaken) {
        Team2WicketsTaken = team2WicketsTaken;
    }

    //Teams Comparison function used in schedule
    public boolean teamsComparison(Object o){
        if(this == o) return true;
        if(this.getClass() != o.getClass()) return false;

        Match tempSchedule = (Match) o;
        if(this.team1.getName().equals(tempSchedule.getTeam1().getName())  || (this.team1.getName().equals(tempSchedule.getTeam2().getName()))){
            return true;
        }
            return false;
    }

    //Display Matches For Schedule
    public String displayMatch(){
        return team1.getName() +"  vs  "+ team2.getName() + "          || Ground: "+ venue.getGroundName()+ "   ||  Date: "+date;
    }

    public void decideWinner() {
        if (Team1Runs > Team2Runs) {
            result = team1.getName();
            team1.getStats().setMatchesWon(team1.getStats().getMatchesWon() + 1);
            team1.getStats().setPoints(team1.getStats().getPoints() + 2);
        } else if (Team2Runs > Team1Runs) {
            result = team2.getName();
            team2.getStats().setMatchesWon(team2.getStats().getMatchesWon() + 1);
            team2.getStats().setPoints(team2.getStats().getPoints() + 2);
        } else {
            result = "Tie";
        }
    }

        public String writeMatchResult(){
        return team1+","+team2+","+Team1Runs+","+Team2Runs+","+Team1WicketsFallen+","+Team2WicketsFallen+","+Team1WicketsTaken+","+Team2WicketsTaken+","+result+","+playStatus+"\n";
    }


}
