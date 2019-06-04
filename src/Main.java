import java.awt.event.MouseListener;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int gameFieldSize = input.nextInt();
        int team1DefendersNumber = input.nextInt();
        int team2DefendersNumber = input.nextInt();
        new GameManagement(gameFieldSize, team1DefendersNumber + team2DefendersNumber);
        input.nextLine();
        for (int i = 0; i < GameManagement.getDefenders().length; i++) {
//            int id = input.nextInt();
//            String type = input.next().trim();
//            int row = input.nextInt();
//            int column = input.nextInt();
            String inputString = input.nextLine();
            String[] infos = inputString.split(",");
            if (infos[1].equals("SwordMan")) {
                GameManagement.setDefender(i, new SwordMan(Integer.parseInt(infos[0]), (i < team1DefendersNumber) ? (1) : (2), GameManagement.getLocation(Integer.parseInt(infos[3]), Integer.parseInt(infos[2]))));
            } else if (infos[1].equals("SpearMan")) {
                GameManagement.setDefender(i, new SpearMan(Integer.parseInt(infos[0]), (i < team1DefendersNumber) ? (1) : (2), GameManagement.getLocation(Integer.parseInt(infos[3]), Integer.parseInt(infos[2]))));
            } else if (infos[1].equals("Tower")) {
                GameManagement.setDefender(i, new Tower(Integer.parseInt(infos[0]), (i < team1DefendersNumber) ? (1) : (2), GameManagement.getLocation(Integer.parseInt(infos[3]), Integer.parseInt(infos[2]))));
            } else
                System.out.println("ERROR: Invalid type.");
        }
        GameManagement.sortDefendersByID();
        while (true) {
            String command = input.next().trim();
            if (command.equals("tick")) {
                int howManyTimes = input.nextInt();
                for (int i = 0; i < howManyTimes; i++)
                    GameManagement.tick();
                for (int i = 0; i < GameManagement.getDefenders().length; i++) {
                    if (GameManagement.getDefenders()[i].isAlive())
                        System.out.println(GameManagement.getDefenders()[i].getId() + "," + GameManagement.getDefenders()[i].getType() + "," + GameManagement.getDefenders()[i].getHp() + "," + GameManagement.getDefenders()[i].getLocation().getRow() + "," + GameManagement.getDefenders()[i].getLocation().getColumn());
                    else if (!GameManagement.getDefenders()[i].isAlive())
                        System.out.println(GameManagement.getDefenders()[i].getId() + "," + GameManagement.getDefenders()[i].getType() + "," + GameManagement.getDefenders()[i].getHp() + ",-1,-1");
                }
            } else if (command.equals("print")) {
                GameManagement.printGameField();
            } else if (command.equals("terminate")) {
                break;
            }
        }
    }
}
