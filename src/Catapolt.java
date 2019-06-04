import java.util.ArrayList;

public class Catapolt extends Weapon {
    public Catapolt(Defender holder) {
        super(holder, 2500, 10);
    }

    @Override
    public ArrayList<Location> getAttackableLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        for (int i = holder.getLocation().getRow() - 2; i <= holder.getLocation().getRow() + 2; i++) {
            for (int j = holder.getLocation().getColumn() - 2; j <= holder.getLocation().getColumn() + 2; j++) {
                if (GameManagement.getLocation(j, i) != null && (i != holder.getLocation().getRow() || j != holder.getLocation().getColumn()))
                    locations.add(GameManagement.getLocation(j, i));
            }
        }
        return locations;
    }
}
