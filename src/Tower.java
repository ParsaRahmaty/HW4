import java.util.ArrayList;

public class Tower extends Defender {
    public Tower(int id, int team, Location location) {
        super(id, team, location, null, null, 0, 10000);
        type = "Tower";
        setWeapon(new Catapolt(this));
        setShield(new RockShield(this));
        setAttackDelay(weapon.getAdditionalAttackDelay());
    }

    @Override
    public Action act() {
        ArrayList<Soldier> attackableSoldiers = new ArrayList<>();
        for (Location location : weapon.getAttackableLocations()) {
            if (location.getDefender() != null && location.getDefender() instanceof Soldier && location.getDefender().getTeam() != team)
                attackableSoldiers.add((Soldier) location.getDefender());
        }
        if (attackableSoldiers.size() >= 1) {
            if (canHit()) {
                Soldier target;
                if (attackableSoldiers.size() > 1) {
                    for (int i = 0; i < attackableSoldiers.size() - 1; i++) {
                        if (attackableSoldiers.get(i).getId() < attackableSoldiers.get(i + 1).getId()) {
                            Soldier tmp = attackableSoldiers.get(i + 1);
                            attackableSoldiers.add(i + 1, attackableSoldiers.get(i));
                            attackableSoldiers.remove(i + 2);
                            attackableSoldiers.add(i, tmp);
                            attackableSoldiers.remove(i + 1);
                        }
                    }
                    target = attackableSoldiers.get(attackableSoldiers.size() - 1);
                } else {
                    target = attackableSoldiers.get(0);
                }
                return new AttackAction(this, target);
            } else {
                return new AttackAction(this, null);
            }
        } else {
            return new AttackAction(this, null);
        }
    }
}
