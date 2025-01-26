public abstract class Player {
    private String name;
    private String jerseyNumber;
    private String age;
    private String country;
    private String team;
    private String opposition;
    private String role;
    private PlayerStats stats = new PlayerStats();
    private boolean playStatus = false;

    public Player(){

    }
    public Player(String name, String role) {
        this.name = name;

    }

    public Player(String name, String jerseyNumber, String role, String age, String country, String team) {
        this.name = name;
        this.jerseyNumber = jerseyNumber;
        this.age = age;
        this.country = country;
        this.team = team;
    }


    public String getOpposition() {
        return opposition;
    }

    public void setOpposition(String opposition) {
        this.opposition = opposition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getJerseyNumber() {
        return jerseyNumber;
    }

    public void setJerseyNumber(String jerseyNumber) {
        this.jerseyNumber = jerseyNumber;
    }

    public void setRole(String role){
        this.role = role;
    }
    public String getRole(){
        return role;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public PlayerStats getStats() {
        return stats;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public void setStats(PlayerStats stats) {
        this.stats = stats;
    }

    public boolean getPlayStatus() {
        return playStatus;
    }

    public void setPlayStatus(boolean playStatus) {
        this.playStatus = playStatus;
    }

    public abstract String addToFile();

    public abstract void displayData();

    public abstract String writeToPlayersScores();



}
