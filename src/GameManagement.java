import java.util.ArrayList;

public class GameManagement {
    private static int timer;
    private static Location[][] gameField;
    private static Defender[] defenders;

    public GameManagement(int gameFieldSize, int defendersCount) {
        gameField = new Location[gameFieldSize][gameFieldSize];
        for (int i = 0; i < gameFieldSize; i++)
            for (int j = 0; j < gameFieldSize; j++)
                gameField[i][j] = new Location(i, j);
        defenders = new Defender[defendersCount];
        timer = 1;
    }

    public static void setDefender(int index, Defender defender) {
        defenders[index] = defender;
    }

    public static int getTimer() {
        return timer;
    }

    public static Defender[] getDefenders() {
        return defenders;
    }

    public static void tick() {
        Defender[] aliveDefenders = getAliveDefenders();
        Action[] actions = new Action[aliveDefenders.length];
        for (int i = 0; i < aliveDefenders.length; i++)
            actions[i] = aliveDefenders[i].act();
        for (Action action : actions)
            action.doAction();
        updateAndHandleLocations();
        timer++;
    }

    public static Location[][] getGameField() {
        return gameField;
    }

    public static Location getLocation(int column, int row) {
        if (0 <= row && 0 <= column && row < gameField.length && column < gameField.length)
            return gameField[column][row];
        else
            return null;
    }

    public static void sortDefendersByID() {
        for (int i = 0; i < defenders.length - 1; i++) {
            for (int j = i + 1; j < defenders.length; j++) {
                if (defenders[i].getId() > defenders[j].getId()) {
                    Defender tmp = defenders[i];
                    defenders[i] = defenders[j];
                    defenders[j] = tmp;
                }
            }
        }
    }

    public static void printGameField() {
        for (int j = 0; j < gameField.length; j++) {
            for (int i = 0; i < gameField.length; i++) {
                if (GameManagement.getLocation(i, j).getDefender() == null)
                    System.out.print("*** ");
                else
//                else if (GameManagement.getLocation(i, j).getDefender() instanceof Tower) {
//                    if (GameManagement.getLocation(i, j).getDefender().getTeam() == 1)
//                        System.out.print("TO1 ");
//                    else if (GameManagement.getLocation(i, j).getDefender().getTeam() == 2)
//                        System.out.print("TO2 ");
//                } else if (GameManagement.getLocation(i, j).getDefender() instanceof SwordMan) {
//                    if (GameManagement.getLocation(i, j).getDefender().getTeam() == 1)
//                        System.out.print("SW1 ");
//                    else if (GameManagement.getLocation(i, j).getDefender().getTeam() == 2)
//                        System.out.print("SW2 ");
//                } else if (GameManagement.getLocation(i, j).getDefender() instanceof SpearMan) {
//                    if (GameManagement.getLocation(i, j).getDefender().getTeam() == 1)
//                        System.out.print("SP1 ");
//                    else if (GameManagement.getLocation(i, j).getDefender().getTeam() == 2)
//                        System.out.print("SP2 ");
                    System.out.print(GameManagement.getLocation(i, j).getDefender().getHp() + " ");
//                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println("_____________________________");
        System.out.println();
        System.out.println();
    }

    private static void updateAndHandleLocations() {
        /*for (Location[] locations : gameField) {
            for (Location location : locations) {
                if (location.getDefender() instanceof Soldier)
                    location.setDefender(null);
            }
        }
        Defender[] aliveDefenders = getAliveDefenders();
        for (int i = 0; i < aliveDefenders.length && aliveDefenders[i] instanceof Soldier; i++) {
            if (aliveDefenders[i].getLocation().getDefender() == null) {
                aliveDefenders[i].getLocation().setDefender(aliveDefenders[i]);
            } else if (aliveDefenders[i].getLocation().getDefender() != null && aliveDefenders[i].getPreviousLocation().getDefender() == null) {
                aliveDefenders[i].setLocation(aliveDefenders[i].getPreviousLocation());
                aliveDefenders[i].getLocation().setDefender(aliveDefenders[i]);
            } else {
                int x = aliveDefenders[i].getLocation().getX();
                int y = aliveDefenders[i].getLocation().getY();
                int tmp = 1;
                System.out.println("YAY?");
                Location destination = aliveDefenders[i].getLocation();
                for (; (tmp == 1) ? (x < gameField.length) : (x >= 0); x += tmp) {
                    if (((Soldier) aliveDefenders[i]).isFacingUp()) {
                        while (getLocation(x, y).getDefender() != null && y < gameField.length)
                            y++;
                        if (y == gameField.length) {
                            ((Soldier) aliveDefenders[i]).setFacingUp(false);
                            y--;
                            while (getLocation(x, y).getDefender() != null && y >= 0)
                                y--;
                            if (getLocation(x, y).getDefender() == null) {
                                destination = getLocation(x, y);
                                break;
                            }
                        } else {
                            destination = getLocation(x, y);
                            break;
                        }
                    } else {
                        while (getLocation(x, y).getDefender() != null && y >= 0)
                            y--;
                        if (y == -1) {
                            ((Soldier) aliveDefenders[i]).setFacingUp(true);
                            y++;
                            while (getLocation(x, y).getDefender() != null && y < gameField.length)
                                y++;
                            if (getLocation(x, y).getDefender() == null) {
                                destination = getLocation(x, y);
                                break;
                            }
                        } else {
                            destination = getLocation(x, y);
                            break;
                        }
                    }
                    if (x == gameField.length - 1) {
                        x = aliveDefenders[i].getLocation().getX();
                        tmp = -1;
                    }
                }
                aliveDefenders[i].setLocation(destination);
                aliveDefenders[i].getLocation().setDefender(aliveDefenders[i]);
            }
        }*/
        for (int i = 0; i < gameField.length; i++) {
            for (int j = 0; j < gameField.length; j++) {
                gameField[i][j].setDefender(null);
            }
        }
        Defender[] aliveDefenders = getAliveDefenders();
        for (Defender aliveDefender : aliveDefenders) {
            if (aliveDefender.getLocation() == aliveDefender.getPreviousLocation()) {
                aliveDefender.getLocation().setDefender(aliveDefender);
            }
        }
        for (int i = 0; i < aliveDefenders.length; i++) {
            for (int j = 0; j < aliveDefenders.length; j++) {
                if (aliveDefenders[j].getLocation() != aliveDefenders[j].getPreviousLocation()) {
                    if (aliveDefenders[j].getLocation().getDefender() != null && aliveDefenders[j].getPreviousLocation().getDefender() == null) {
                        aliveDefenders[j].setLocation(aliveDefenders[j].getPreviousLocation());
                        aliveDefenders[j].getLocation().setDefender(aliveDefenders[j]);
                    }
                }
            }
        }
        for (int i = 0; i < aliveDefenders.length; i++) {
            if (aliveDefenders[i].getLocation() != aliveDefenders[i].getPreviousLocation()) {
                if (aliveDefenders[i].getLocation().getDefender() == null) {
                    aliveDefenders[i].getLocation().setDefender(aliveDefenders[i]);
                } else if (aliveDefenders[i].getLocation().getDefender() != null && aliveDefenders[i].getPreviousLocation().getDefender() == null) {
                    aliveDefenders[i].setLocation(aliveDefenders[i].getPreviousLocation());
                    aliveDefenders[i].getLocation().setDefender(aliveDefenders[i]);
                } else {
                    int row = aliveDefenders[i].getLocation().getRow();
                    int column = aliveDefenders[i].getLocation().getColumn();
                    int tmp = 1;
                    Location destination = aliveDefenders[i].getLocation();
                    for (; (tmp == 1) ? (column < gameField.length) : (column >= 0); column += tmp) {
                        if (((Soldier) aliveDefenders[i]).isFacingUp()) {
                            while (getLocation(column, row).getDefender() != null && row >= 0)
                                row--;
                            if (row == -1) {
                                ((Soldier) aliveDefenders[i]).setFacingUp(false);
                                row++;
                                while (getLocation(column, row).getDefender() != null && row < gameField.length)
                                    row++;
                                if (getLocation(column, row).getDefender() == null) {
                                    destination = getLocation(column, row);
                                    break;
                                }
                            } else {
                                destination = getLocation(column, row);
                                break;
                            }
                        } else {
                            while (getLocation(column, row).getDefender() != null && row < gameField.length)
                                row++;
                            if (row == gameField.length) {
                                ((Soldier) aliveDefenders[i]).setFacingUp(true);
                                row--;
                                while (getLocation(column, row).getDefender() != null && row >= 0)
                                    row--;
                                if (getLocation(column, row).getDefender() == null) {
                                    destination = getLocation(column, row);
                                    break;
                                }
                            } else {
                                destination = getLocation(column, row);
                                break;
                            }
                        }
                        if (column == gameField.length - 1) {
                            column = aliveDefenders[i].getLocation().getColumn();
                            tmp = -1;
                        }
                    }
                    aliveDefenders[i].setLocation(destination);
                    aliveDefenders[i].getLocation().setDefender(aliveDefenders[i]);
                }
            }
        }
    }

    private static Defender[] getAliveDefenders() {
        ArrayList<Integer> indexes = new ArrayList<>();
        for (int i = 0; i < defenders.length; i++) {
            if (defenders[i].isAlive)
                indexes.add(i);
        }
        Defender[] aliveDefenders = new Defender[indexes.size()];
        for (int i = 0; i < indexes.size(); i++)
            aliveDefenders[i] = defenders[indexes.get(i)];
        return aliveDefenders;
    }
}
// && aliveDefenders[i].getPreviousLocation() != aliveDefenders[i].getLocation()