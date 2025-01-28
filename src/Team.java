import java.util.ArrayList;

public class Team {
    private String teamName;
    private String captain;
    private String coach;
    private String city;
    private String opposition;
    private ArrayList<Player> players = new ArrayList<>();

    private TeamStats stats = new TeamStats();

    public Team(String teamName, String captain, String coach,String city) {
        this.teamName = teamName;
        this.captain = captain;
        this.coach = coach;
        this.city = city;
    }

    public Team(Team team){
        this.teamName = team.getTeamName();
        this.captain = team.getCaptain();
        this.coach = team.getCoach();
        this.city = team.getCity();
        this.stats = new TeamStats(team.getStats());
    }
    public String getCaptain() {
        return captain;
    }

    public String getCoach() {
        return coach;
    }

    public String getName(){
        return teamName;
    }

    public void setPlayer(Player player){
        this.players.add(player);
    }

    public void setPlayerList(){
        this.players = new ArrayList<>();
    }

    public ArrayList<Player> getPlayer(){
        return players;
    }

    public String getCity() {
        return city;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setCaptain(String captain) {
        this.captain = captain;
    }

    public void setCoach(String coach) {
        this.coach = coach;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public TeamStats getStats() {
        return stats;
    }

    public String initialFileData(){
        return teamName+","+opposition+","+captain+","+coach+","+ city+"\n";
    }

    public void displayTeam(){
        if(players != null){
            for(Player player: players){
                player.displayData();
            }
        }
    }

}
