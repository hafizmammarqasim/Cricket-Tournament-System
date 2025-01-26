public class Bowler extends Player implements WicketsTaken {
    private int wicketsTaken;

    public Bowler(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public Bowler(String name, String role, int wicketsTaken) {
        super(name, role);
        this.wicketsTaken = wicketsTaken;
    }

    public Bowler(String name, String jerseyNumber, String role, String age, String country, String team) {
        super(name, jerseyNumber, role, age, country, team);
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void setWicketsTaken(int wicketsTaken) {
        this.wicketsTaken = wicketsTaken;
    }

    public void assignData(String name, String team, String opposition, String wicketsTaken,String playStatus){
        setName(name);
        setTeam(team);
        setOpposition(opposition);
        setWicketsTaken(Integer.parseInt(wicketsTaken));
        setPlayStatus(Boolean.parseBoolean(playStatus));
    }


    public String writeToPlayersScores(){
        return getName()+","+getTeam()+","+getOpposition()+","+getWicketsTaken()+","+getPlayStatus()+"\n";
    }

    public String addToFile(){
        return getName()+","+getJerseyNumber()+","+getRole()+","+getAge()+","+getCountry()+","+getTeam()+"\n";
    }

    public void displayData(){
        System.out.println("Player Name: "+getName()+"  || Player Role: "+getRole()+"  || Jersey Number: "+getJerseyNumber()+" || Age: "+getAge()+" || Country: "+getCountry()+" || Team: "+getTeam());
    }

    
}
