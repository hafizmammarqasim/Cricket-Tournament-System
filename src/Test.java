import java.io.IOException;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws IOException {
        Scanner myObj = new Scanner(System.in);
        Tournament tournament = new Tournament();
        tournament.readData();
        while (true){
            System.out.println("Choose the function you want to perform");
            System.out.println("1. Team");
            System.out.println("2. Add Ground");
            System.out.println("3. Player");
            System.out.println("4. Schedule");
            int choice = myObj.nextInt();
            myObj.nextLine();
            switch (choice){
                case 1:
                    tournament.addTeam();
                    break;
                case 2:
                    tournament.addGround();
                    break;
                case 3:
                    tournament.Player();
                    break;
                case 4:
                    tournament.Schedule();
                    break;
                case 5:
                    tournament.pointsTable();
                    break;
            }
        }
    }
}

