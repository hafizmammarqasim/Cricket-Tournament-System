public class Batsman extends Player implements RunsScored,Displayable{
    private int runsScored;

    public Batsman(String name, String role) {
        super(name, role);
    }

    public Batsman(String name, String jerseyNumber, String role, String age, String country, String team) {
        super(name, jerseyNumber, role, age, country, team);
    }

    public int getRunsScored() {
        return runsScored;
    }

    public void setRunsScored(int runsScored) {
        this.runsScored = runsScored;
    }

    public void assignData(String name, String team, String opposition, String runsScored ,String playStatus){
        setName(name);
        setTeam(team);
        setOpposition(opposition);
        setRunsScored(Integer.parseInt(runsScored));
        setPlayStatus(Boolean.parseBoolean(playStatus));
    }

    public String writeToPlayersScores(){
        return getName()+","+getTeam()+","+getOpposition()+","+getRunsScored()+","+getPlayStatus()+"\n";
    }

    public String addToFile(){
        return getName()+","+getJerseyNumber()+","+getRole()+","+getAge()+","+getCountry()+","+getTeam()+"\n";
    }

    public void displayData(){
        System.out.println("Player Name: "+getName()+"  || Player Role: "+getRole()+"  || Jersey Number: "+getJerseyNumber()+" || Age: "+getAge()+" || Country: "+getCountry()+" || Team: "+getTeam());
    }


}
