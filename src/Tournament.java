import java.io.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDate;

public class Tournament {
    private ArrayList <Team> teams = new ArrayList<>();
    private ArrayList <Match> matches = new ArrayList<>();
    private Schedule schedule = new Schedule();
    private ArrayList <Ground> grounds = new ArrayList<>();

    Scanner myObj = new Scanner(System.in);

    public void readData(){
        readTeams();
        readGround();
    }

    public void addTeam(){
        displayTeams();
        String line;
        File file = new File("teams.txt");

        if(!file.exists())
            try {
                if(file.createNewFile()) {
                    addNewTeam(file);
                }
                else{
                    System.out.println("Could not create new File "+file);
                }
            } catch (IOException e){
                System.out.println(e.getMessage());
            }
        choiceForNewTeam(file);
    }

    public void choiceForNewTeam(File file){
        System.out.println("--------------------");
        System.out.println("Add new Team?");
        int choice = myObj.nextInt();
        myObj.nextLine();

        switch (choice){
            case 1:
                addNewTeam(file);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid Entry");
        }

    }

    public void addNewTeam(File file){
        System.out.println("Enter the name of team:");
        String teamName = myObj.nextLine();
        System.out.println("Enter the captain Name");
        String captainName = myObj.nextLine();
        System.out.println("Enter the coach Name");
        String coachName = myObj.nextLine();
        System.out.println("Enter Home City");
        String city = myObj.nextLine();

        try {
            Team tempTeam = new Team(teamName, captainName, coachName,city);
            teams.add(tempTeam);
            FileWriter writer = new FileWriter(file,true);
            writer.write(tempTeam.initialFileData());
            writer.close();
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }

    public void displayTeams(){
        if(!teams.isEmpty()) {
            System.out.println("--------------------");
            System.out.println("Available Teams");
            int i = 0;
            while (i < teams.size()) {
                System.out.println(teams.get(i).getName());
                i++;
            }
        }
    }

    public void Schedule() throws IOException {
        Match tempMatch;
        Ground tempGround;
        schedule = new Schedule();
        Schedule remainingMatches = new Schedule();
        matches = new ArrayList<>();
        //Syntax for Date
        LocalDate date = LocalDate.of(2025,1,15);

        //Creating random matches
        int size = teams.size();
        if(size>=2) {
            for (int i = 0; i < size-1; i++) {
                for (int j=i; j<size; j++) {
                    if (teams.get(i) != teams.get(j)){
                        tempGround = checkGround(teams.get(i),teams.get(j));
                        tempMatch = new Match(teams.get(i),teams.get(j),(tempGround!=null)? tempGround : new Ground("Gadafi","Lahore","30000","94m") );
                        matches.add(tempMatch);
                    }}}

            if(!matches.isEmpty()){
                for(Match matches: matches){
                    remainingMatches.addNewMatch(matches); }
            }

            File file = new File("MatchResult");
            if(file.exists()) {
                for (Match match : remainingMatches.getMatches()) {
                    readWinStatus(match,file);
                }
            } else {
                System.out.println("Past Data is not available. File does not exist");
            }

            //Sorting Matches
            int counter =0;
            boolean status;
            do{
                status = false;
                for(int i=0; i<remainingMatches.getMatches().size(); i++) {
                    if (schedule.getMatches().isEmpty() || remainingMatches.getMatches().get(i) != null && !(remainingMatches.getMatches().get(i).teamsComparison(schedule.getMatches().getLast()))) {
                        tempMatch = remainingMatches.getMatches().get(i);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
                        tempMatch.addDate(date.format(formatter));
                        date = date.plusDays(1);
                        schedule.addNewMatch(tempMatch);
                        remainingMatches.getMatches().remove(i);
                        status = true;
                    }
                }
            } while(!remainingMatches.getMatches().isEmpty() && status);

            //Bachay huay matches yaha se assign houn ge
            if(!remainingMatches.getMatches().isEmpty()){
                for (Match remainingmatch : remainingMatches.getMatches()) {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM dd,yyyy");
                    tempMatch = remainingmatch;
                    tempMatch.addDate(date.format(formatter));
                    schedule.addNewMatch(tempMatch);
                    date = date.plusDays(1);
                }
            }
            schedule.displayMatches();
           match(schedule.getMatches().get(0));
        }
    }

    //We are matching the suitable ground for the schedule here
    public Ground checkGround(Team team1, Team team2){
        for (Ground ground : grounds) {
            if (team1.getCity().equals(ground.getCity()) || team2.getCity().equals(ground.getCity()))
                return ground;
        }
        return  !grounds.isEmpty() ?  grounds.getFirst() : null;
    }

    public void Player() throws IOException {
        File file = new File("players.txt");
        if(file.exists()) {
            addPlayer(teams.getFirst(), file);   //Ye button ke andar ho ga na, to jis button k andar ho ga uska function yaha pass kr dena
            //E.g Lahore ke andar lahore ka
            //jo object banay wohi pass krna ha
        } else {
            if(file.createNewFile()){
                addPlayer(teams.getFirst(),file);
            }
        }
        System.out.println("Do you want to delete player:" );
        int choice = myObj.nextInt();
        myObj.nextLine();

        switch (choice){
            case 1:
                deletePlayer(teams.getFirst(), file);
                break;
        }
    }

    public void readWinStatus(Match match, File file){
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            reader.readLine();
            String line;
            while ((line = reader.readLine())!=null){
                String[] data = line.split(",");
                if(match.getTeam1().equals(data[0]) && match.getTeam2().equals(data[1])){
                    match.setResult(data[2]);
                    return;
                }
            }
        } catch (IOException e){
            System.out.println("Error in reading Match Result File "+e.getMessage());
        }
    }

    public void addPlayer(Team team,File file){
        team.displayTeam();
        Player tempPlayer;
        if( team.getPlayer().isEmpty()){ //agar array list empty hai tou ye call ho ga
            tempPlayer = addCaptain(team);
        }
        else if((!(team.getPlayer().isEmpty()))  && team.getPlayer().size()<=5){
                tempPlayer = playerDetails(team);
        } else {
            System.out.println("Your team already has Six Players, You cannot add more player");
            return;
        }
            try (FileWriter writer = new FileWriter(file, true)) {
                writer.write(tempPlayer.addToFile());
                team.setPlayer(tempPlayer);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("Try block 3");
            }
    }

    public Player addCaptain(Team team){
        System.out.println("Add Captain");
        System.out.println("-----------------------");
        System.out.println("Enter Details of Captain("+team.getCaptain()+")");
        System.out.println("Enter Jersey Number: ");
        String jerseyNumber = myObj.nextLine();
        System.out.println("Enter Role");
        String role = myObj.nextLine();
        System.out.println("Enter age: ");
        String age = myObj.nextLine();
        System.out.println("Enter country");
        String country = myObj.nextLine();

        if(role.contains("at"))
           return new Batsman(team.getCaptain(),jerseyNumber,role,age,country,team.getName());
        else if(role.contains("owl"))
            return new Bowler(team.getCaptain(),jerseyNumber,role,age,country,team.getName());
        else if (role.contains("oun"))
            return new AllRounder(team.getCaptain(), jerseyNumber,role,age,country,team.getName());
        else
            return new Keeper(team.getCaptain(),jerseyNumber,role,age,country,team.getName());
    }

    public Player playerDetails(Team team){
        String name, jerseyNumber,role,age,country;
        System.out.println("Enter player Details");
        System.out.println("----------------------");
        System.out.println("Give player's name:");
        name = myObj.nextLine();
        System.out.println("Enter Jersey Number: ");
        jerseyNumber = myObj.nextLine();
        System.out.println("Enter Role");
        role = myObj.nextLine();
        System.out.println("Enter age: ");
        age = myObj.nextLine();
        System.out.println("Enter country");
        country = myObj.nextLine();
        if(role.contains("at"))
            return new Batsman(name,jerseyNumber,role,age,country,team.getName());
        else if(role.contains("owl"))
            return new Bowler(name,jerseyNumber,role,age,country,team.getName());
        else if (role.contains("oun"))
            return new AllRounder(name,jerseyNumber,role,age,country,team.getName());
        else
            return new Keeper(name,jerseyNumber,role,age,country,team.getName());
    }

    //File se data read karnay ke liya ye function bnaya hai
    public void readPlayerData(Team team){
        File file = new File("players.txt");
        if(file.exists()) {
            //Assigning new array list to avoid duplicating players
            team.setPlayerList();
            //reading Players from file
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while (((line = reader.readLine()) != null)) {
                    String[] tempPlayerArray = line.split(",");
                    int size = tempPlayerArray.length;
                    if (team.getName().equals(tempPlayerArray[size - 1])) {
                        if(tempPlayerArray[2].contains("at"))
                        team.setPlayer(new Batsman(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
                        else if(tempPlayerArray[2].contains("wl"))
                            team.setPlayer(new Bowler(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
                        else if (tempPlayerArray[2].contains("oun")) {
                            team.setPlayer(new AllRounder(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
                        } else
                            team.setPlayer(new Keeper(tempPlayerArray[0], tempPlayerArray[1], tempPlayerArray[2], tempPlayerArray[3], tempPlayerArray[4], tempPlayerArray[5]));
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                System.out.println("catch block of readPlayerData");
            }
        }
    }

    //New Function --Delete Player--
    public void deletePlayer(Team team, File file){
        boolean flag = false;
        System.out.println("Enter Player's Name:");
        String playerName = myObj.nextLine();

        if(team.getCaptain().equals(playerName)){
            System.out.println("You cannot remove the Captain ("+ team.getCaptain()+") from here");
            return;
        }
        String tempName = file.getName();
        readPlayerData(team);

        if(file.exists()) {
            file.delete();
        }

        File newFile = new File(tempName);

        for (int i=1; i<team.getPlayer().size(); i++){
            if(team.getPlayer().get(i).getName().equals(playerName)){
                team.getPlayer().remove(i);
                System.out.println("Player Successfully Deleted");
                break;
            }
        }

        try (FileWriter writer = new FileWriter(newFile)){
            for (int i=0; i<team.getPlayer().size(); i++){
                if(team.getPlayer().get(i)!=null){
                    writer.write(team.getPlayer().get(i).addToFile());
                }
            }
        } catch (IOException e){
            System.out.println(e.getMessage());
        }

    }

    public void addGround() throws IOException {

        File file = new File("grounds.txt");
            if(!file.exists()) {
                if (file.createNewFile()) {
                    System.out.println("No Grounds to show");
                    addNewGround(file);
                } else{
                    System.out.println("There was some error in creating the File ("+file.getName()+")");
                }
            }
        for(Ground ground: grounds){
            ground.displayDetails();
        }
        System.out.println("-----------------------");
        choiceForGround(file);
    }

    public void choiceForGround(File file){
        System.out.println("\t\tPress");
        System.out.println("1. To add new Ground");
        System.out.println("0. To Return");
        int choice = myObj.nextInt();
        myObj.nextLine();

        switch (choice){
            case 1:
                addNewGround(file);
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid entry, try again");
                System.out.println("---------------------------");
                choiceForGround(file);
        }
    }


    public void addNewGround(File file){
        System.out.println("Enter the details of ground");
        System.out.println("Enter ground name:");
        String name = myObj.nextLine();
        System.out.println("Enter city name");
        String city = myObj.nextLine();
        System.out.println("Enter ground capacity");
        String capacity = myObj.nextLine();
        System.out.println("Enter ground length");
        String length = myObj.nextLine();


        try(FileWriter writer = new FileWriter(file,true)){
            Ground tempGround = new Ground(name,city,capacity,length);
            grounds.add(tempGround);
            writer.write(tempGround.writeToFile());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
    }


    //Match Creation Class
    public void match(Match match) throws IOException{
       readPlayersRunsFromFile(match);
        teamScores(match);
    }

    public void teamScores(Match match) throws IOException{
        int TotalScoreOfT1, TotalScoreOfT2;

        if(!match.getPlayStatus()) {
            System.out.println("Enter " + match.getTeam1().getName() + " wickets fallen");
            int teamOneWF = myObj.nextInt();
            myObj.nextLine();
            match.setTeam1Runs(playerScores(teamOneWF, match.getTeam1()));
            playerWickets(match.getTeam2());
            match.setTeam1WicketsFallen(teamOneWF);
            match.setTeam2WicketsTaken(teamOneWF);
            System.out.println("Enter " + match.getTeam2().getName() + "wickets fallen");
            int teamTwoWF = myObj.nextInt();
            myObj.nextLine();
            match.setTeam2WicketsFallen(playerScores(teamTwoWF, match.getTeam2()));
            match.setTeam2WicketsTaken(teamTwoWF);
            match.setTeam1WicketsTaken(teamTwoWF);
            playerWickets(match.getTeam1());

            writePlayersRunsToFile(match.getTeam1());
            writePlayersRunsToFile(match.getTeam2());
            match.setPlayStatus(true);

        }
        displayData(match);
    }

    public void writePlayersRunsToFile(Team team) throws IOException {
        File file = new File("playersScores.txt");
        if(file.exists()){
            try (FileWriter writer = new FileWriter(file,true)){
                for (Player player: team.getPlayer())
                    writer.write(player.writeToPlayersScores());
            } catch (IOException e){
                System.out.println("Error in opening file for Writing player's data");
            }
        } else if(file.createNewFile()){
            try (FileWriter writer = new FileWriter(file)){
                for (Player player: team.getPlayer())
                    writer.write(player.writeToPlayersScores());
            } catch (IOException e){
                System.out.println("Error in opening file for Writing player's data");
            }
        }
    }


   public void readPlayersRunsFromFile(Match match){
        readPlayerData(match.getTeam1());
        readPlayerData(match.getTeam2());
         for (Player player:match.getTeam1().getPlayer()){
               player.setOpposition(match.getTeam2().getName());
         }

         for (Player player:match.getTeam2().getPlayer()) {
            player.setOpposition(match.getTeam1().getName());
         }

        ReadTeamPlayers(match.getTeam1(), match.getTeam2());
        ReadTeamPlayers(match.getTeam2(),match.getTeam1());
   }

   public void ReadTeamPlayers(Team team, Team opposition){
       File file = new File("playersScores.txt");
        if(file.exists()){
            try(BufferedReader reader = new BufferedReader(new FileReader(file))){
                String line;
                while((line= reader.readLine())!=null){
                  String[] data = line.split(",");
                   for(Player player:team.getPlayer()){
                        if(player!=null){
                       if(player.getName().equalsIgnoreCase(data[0]) && opposition.getName().equals(data[2])) {
                           switch (player) {
                               case AllRounder allrounder -> allrounder.assignData(data[0], data[1], data[2], data[3], data[5], data[6]);
                               case Batsman batter -> batter.assignData(data[0], data[1], data[2], data[3], data[5]);
                               case Bowler bowler -> bowler.assignData(data[0], data[1], data[2], data[3], data[5]);
                               case Keeper keeper -> keeper.assignData(data[0], data[1], data[2], data[3], data[5], data[6]);
                               default -> System.out.println("No Required Player Type Found");
                           }
                              player.displayData();
                              myObj.nextLine();
                               break;
                           }
                       }
                   }
             }
          } catch (IOException e){
                System.out.println("error in reading data from players file");
            }
      }
    }


    public int playerScores(int wickets, Team team){
        int score;
       int teamRuns = 0;
        for(int i=0; i<wickets; i++){
            Player player = team.getPlayer().get(i);
            if(player instanceof RunsScored batter) {
                System.out.println("Enter Score of " + player.getName());
                score = myObj.nextInt();
                batter.setRunsScored(score);
                player.getStats().setTotalRuns(score);
                teamRuns += score;
            }
        }
        return teamRuns;
    }

    public void playerWickets(Team team){
        for (Player player: team.getPlayer()){
            if(player instanceof WicketsTaken bowler){
                System.out.println("Enter wickets taken by "+ player.getName());
                int wicketsTaken = myObj.nextInt(); myObj.nextLine();
                bowler.setWicketsTaken(wicketsTaken);
                player.getStats().setWickets(wicketsTaken);
            }
        }
    }

    public void displayData(Match match){

        ArrayList <Player> batsmenTeam1 = match.getTeam1().getPlayer();
        ArrayList <Player> bowlerTeam1 = match.getTeam1().getPlayer();
        ArrayList <Player> batsmenTeam2 = match.getTeam2().getPlayer();
        ArrayList <Player> bowlerTeam2 = match.getTeam2().getPlayer();
        batsmenTeam1.sort(new SortingRuns());
        //Team 2 Sorting Of Wickets
        bowlerTeam2.sort(new SortingWickets());

        //Team 2 Sorting Of Runs
        batsmenTeam2.sort(new SortingRuns());
        //Team 1 Sorting Of Wickets
        bowlerTeam1.sort(new SortingWickets());
        matchSummary(batsmenTeam1,bowlerTeam1,batsmenTeam2,bowlerTeam2);
        scoreCard(match);
    }

    public void matchSummary(ArrayList <Player> batsmenTeam1, ArrayList <Player> bowlerTeam1, ArrayList <Player> batsmenTeam2, ArrayList <Player> bowlerTeam2 ){
        //Team 1 Sorting Of Runs

        System.out.println("-----Match Summary----");
        System.out.println(batsmenTeam1.getFirst().getTeam()+" Batting");

        for(int i=0; i<3; i++){
            System.out.println(batsmenTeam1.get(i).getName() + batsmenTeam1.get(i).getRunsScored());
        }

        System.out.println("-----------------------------------");

        System.out.println(batsmenTeam2.getFirst().getTeam()+" Bowling");
        for(int i=0; i<3; i++){
            System.out.println(bowlerTeam2.get(i).getName() + bowlerTeam2.get(i).getWicketsTaken());
        }
        System.out.println("-----------------------------------");

        System.out.println(batsmenTeam2.getFirst().getTeam()+" Batting");
        for(int i=0; i<3; i++){
            System.out.println(batsmenTeam2.get(i).getName() + batsmenTeam2.get(i).getRunsScored());
        }
        System.out.println("-----------------------------------");

        System.out.println(batsmenTeam1.getFirst().getTeam()+" Bowling");
        for(int i=0; i<3; i++){
            System.out.println(bowlerTeam1.get(i).getName() + bowlerTeam1.get(i).getWicketsTaken());
        }
        System.out.println("-----------------------------------");
    }

    public void scoreCard(Match match){
        System.out.println("---"+match.getTeam1().getName()+" Batting---");
        for (Player player : match.getTeam2().getPlayer()) {
            System.out.println(player.getName() +"  "+ player.getRunsScored());
        }

        System.out.println("---"+match.getTeam2().getName()+" Bowling---");
        for (Player player : match.getTeam1().getPlayer()) {
            if(player.getRole().contains("owl") || player.getRole().contains("oun"))
                System.out.println(player.getName() +"  " +player.getWicketsTaken());
        }

        System.out.println("---"+match.getTeam2().getName()+" Batting---");
        for (Player player : match.getTeam2().getPlayer()) {
            System.out.println(player.getName() + "  " + player.getRunsScored());
        }

        System.out.println("---"+match.getTeam1().getName()+" Bowling");
        for (Player player : match.getTeam1().getPlayer()) {
            if(player.getRole().contains("owl") || player.getRole().contains("oun"))
                System.out.println(player.getName() + "  " + player.getWicketsTaken());
        }
    }

    public void readTeams(){
        teams = new ArrayList<>();
        File file = new File("teams.txt");
        if(file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;
                while((line = reader.readLine())!=null){
                    String[] data = line.split(",");
                    Team tempTeam = new Team(data[0],data[1],data[2],data[3]);
                    teams.add(tempTeam);
                    readPlayerData(tempTeam);
                }
            } catch (IOException e) {
                System.out.println("ERROR IN Reading Team File");
            }
        }
    }

    public void writeTeamsToFile() {
        if (!teams.isEmpty()) {
            File file = new File("teams.txt");
            if (file.exists()) {
                try (FileWriter writer = new FileWriter(file)) {
                    for (Team team : teams) {
                        writer.write(team.initialFileData());
                    }
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    public void readGround(){
        String line;
        grounds = new ArrayList<>();
        File file = new File("grounds.txt");
        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                while ((line = reader.readLine()) != null) {
                    String[] tempGround = line.split(",");
                    grounds.add(new Ground(tempGround[0], tempGround[1], tempGround[2], tempGround[3]));
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}





