public class AllRounder extends Batsman implements WicketsTaken{
    private int runsScored;
    private int wicketsTaken;


    public AllRounder(String name, String role) {
        super(name, role);
    }

    public void assignData(String name, String team, String opposition, String runsScored ,String wicketsTaken, String playStatus){
        setName(name);
        setTeam(team);
        setOpposition(opposition);
        setRunsScored(Integer.parseInt(runsScored));
        setWicketsTaken(Integer.parseInt(wicketsTaken));
        setPlayStatus(Boolean.parseBoolean(playStatus));
    }
    public AllRounder(String name, String jerseyNumber, String role, String age, String country, String team) {
        super(name, jerseyNumber, role, age, country, team);
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

    public String writeToPlayersScores(){
        return getName()+","+getTeam()+","+getOpposition()+","+getRunsScored()+","+getWicketsTaken()+","+getPlayStatus()+"\n";
    }

    public String addToFile(){
        return getName()+","+getJerseyNumber()+","+getRole()+","+getAge()+","+getCountry()+","+getTeam()+"\n";
    }

    public void displayData(){
        System.out.println("Player Name: "+getName()+"  || Player Role: "+getRole()+"  || Jersey Number: "+getJerseyNumber()+" || Age: "+getAge()+" || Country: "+getCountry()+" || Team: "+getTeam());
    }
}
