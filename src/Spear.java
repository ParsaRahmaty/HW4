import java.util.ArrayList;

public class Spear extends Weapon {
    public Spear(Defender holder) {
        super(holder, 1500, 2);
    }

    @Override
    public ArrayList<Location> getAttackableLocations() {
        ArrayList<Location> locations = new ArrayList<>();
        if (((Soldier) holder).isFacingUp()) {
            for (int i = holder.getLocation().getColumn() - 1; i <= holder.getLocation().getColumn() + 1; i++) {
                for (int j = holder.getLocation().getRow() - 2; j <= holder.getLocation().getRow() - 1; j++) {
                    if (GameManagement.getLocation(i, j) != null)
                        locations.add(GameManagement.getLocation(i, j));
                }
            }
        } else if (!((Soldier) holder).isFacingUp()) {
            for (int i = holder.getLocation().getColumn() - 1; i <= holder.getLocation().getColumn() + 1; i++) {
                for (int j = holder.getLocation().getRow() + 1; j <= holder.getLocation().getRow() + 2; j++) {
                    if (GameManagement.getLocation(i, j) != null) {
                        locations.add(GameManagement.getLocation(i, j));
                    }
                }
            }
        }
        return locations;
    }
}
