import java.util.ArrayList;

public class Sword extends Weapon {
    public Sword(Defender holder) {
        super(holder, 2000, 1);
    }

    @Override
    public ArrayList<Location> getAttackableLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        for (int i = holder.getLocation().getColumn() - 1; i <= holder.getLocation().getColumn() + 1; i++) {
            for (int j = holder.getLocation().getRow() - 1; j <= holder.getLocation().getRow() + 1; j++) {
                if (GameManagement.getLocation(i, j) != null && (i != holder.getLocation().getColumn() || j != holder.getLocation().getRow()))
                    locations.add(GameManagement.getLocation(i, j));
            }
        }
        return locations;
    }
}
