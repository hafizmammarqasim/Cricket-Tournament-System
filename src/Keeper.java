public class Keeper extends Player implements Displayable{
    private int catches;
    private int stumpings;

    public Keeper(int catches) {
        this.catches = catches;
    }

    public Keeper(String name, String role, int catches) {
        super(name, role);
        this.catches = catches;
    }

    public Keeper(String name, String jerseyNumber, String role, String age, String country, String team) {
        super(name, jerseyNumber, role, age, country, team);
    }

    public void setCatches(int catches){
        this.catches = catches;
    }

    public void setStumpings(int stumpings){
        this.stumpings = stumpings;
    }

    public int getCatches(){
        return catches;
    }

    public int getStumpings(){
        return stumpings;
    }

    public void assignData(String name, String team, String opposition, String catches,String stumpings ,String playStatus){
        setName(name);
        setTeam(team);
        setOpposition(opposition);
        setStumpings(Integer.parseInt(stumpings));
        setCatches(Integer.parseInt(catches));
        setPlayStatus(Boolean.parseBoolean(playStatus));
    }

    public String writeToPlayersScores(){
        return getName()+","+getTeam()+","+getOpposition()+","+getCatches()+","+getStumpings()+","+getPlayStatus()+"\n";
    }

    public String addToFile(){
        return getName()+","+getJerseyNumber()+","+getRole()+","+getAge()+","+getCountry()+","+getTeam()+"\n";
    }
    
    public void displayData(){
        System.out.println("Player Name: "+getName()+"  || Player Role: "+getRole()+"  || Jersey Number: "+getJerseyNumber()+" || Age: "+getAge()+" || Country: "+getCountry()+" || Team: "+getTeam());
    }

    
}
