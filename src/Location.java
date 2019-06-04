public class Location {
    private int row;
    private int column;
    private Defender defender;

    public Location(int column, int row) {
        this.row = row;
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }

    public void setDefender(Defender defender) {
        this.defender = defender;
    }

    public Defender getDefender() {
        return defender;
    }
}
